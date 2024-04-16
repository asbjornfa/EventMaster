package GUI.Controller;

import BE.*;
import BLL.EmailSender;
import BLL.QRManager;
import BLL.ReservationManager;
import GUI.Model.*;
import com.google.zxing.WriterException;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddReservationViewController implements Initializable {

    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblTicketType;

    @FXML
    private Label lblEventTitle;


    @FXML
    private TextField txtFieldCostumerEmail;

    private TicketModel ticketModel;
    private ReservationModel reservationModel;
    private PurchasedTicketsModel purchasedTicketsModel;
    private TicketTypeModel ticketTypeModel;
    private EventModel eventModel;
    private ReservationManager reservationManager;
    private Ticket selectedTicket;
    private MainViewController mainViewController;

    public AddReservationViewController() throws Exception {
        ticketModel = new TicketModel();
        reservationModel = new ReservationModel();
        purchasedTicketsModel = new PurchasedTicketsModel();
        ticketTypeModel = new TicketTypeModel();
        eventModel = new EventModel();
        reservationManager = new ReservationManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void setSelectedTicket(Ticket ticket) {
        this.selectedTicket = ticket;
    }

    public void setEventData(String eventTitle, String ticketTypeTitle) {
        lblEventTitle.setText(eventTitle);
        lblTicketType.setText(ticketTypeTitle);
    }


    @FXML
    public void onClickCancel(ActionEvent event) {
        mainViewController.setCenterView("/View/TicketViewTable.fxml");
        mainViewController.lblMenuTitle.setText("Event tickets");
    }

    @FXML
    public void onClickSave(ActionEvent event) throws Exception {

        String email = txtFieldCostumerEmail.getText().trim(); // Get customer email from input field
        String eventTitle = lblEventTitle.getText(); // Get event title from label
        String ticketTypeTitle = lblTicketType.getText();
        int quantity = Integer.parseInt(lblQuantity.getText());

        // Email validation using regex
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        // Check if email field is empty
        if (email.isEmpty()) {
            showAlert("Error", "Email field is empty.", Alert.AlertType.ERROR);
            return;
        }

        // Check if email matches the regex pattern
        if (!matcher.matches()) {
            showAlert("Error", "Invalid email format.", Alert.AlertType.ERROR);
            return;
        }

        // Laver en ny instans af klasen
        EmailSender emailSender = new EmailSender();

        // Genererer en unik UUID baseret på en streng sammensat af email, eventTitle og ticketTypeTitle
        UUID uniqueId = QRManager.generateUniqueUUID(email + eventTitle + ticketTypeTitle);
        // Konverterer den unikke UUID til en streng
        String uniqueString = uniqueId.toString();

        // Genererer en 2D QR-kodebillede baseret på den unikke streng
        QRManager.generate2DQRCodeImage(uniqueString);

        // Initialiserer en File variabel og en Attachment variabel
        File gemtFil = null;
        Attachment attachment = null;
        try {
            // Henter QR-kodefilen ved hjælp af den unikke streng
            gemtFil = QRManager.getQrCodeFile(uniqueString);
            // Tilføjer filen som en vedhæftning til emailen
            attachment = emailSender.addImageAttachment(gemtFil);
        } catch (Exception e) {
            System.out.println("Noget gik galt...\n" + e.getMessage());
        }

        try {
            // Sender emailen med vedhæftningen
            emailSender.sendEmailWithAttachment(email, eventTitle, "Her er din " + ticketTypeTitle + " billet" + ". Vi ses snart til " + eventTitle, attachment);
        } catch (ResendException e) {
            System.out.println("Error trying to send eamil");
        }

        try {
            // Check if email already exists in the database
            Reservations existingReservation = reservationManager.getReservationByEmail(email);
            if (existingReservation != null) {
                // Use the existing reservation ID
                TicketType ticketTypeId = ticketTypeModel.getTicketTypeIdFromTitle(ticketTypeTitle);
                Event eventId = eventModel.getEventIdFromTitle(eventTitle);
                purchasedTicketsModel.createPurchasedTickets(existingReservation.getId(), ticketTypeId.getId(), eventId.getId(), uniqueString, quantity);
            } else {
                // Create a new reservation
                Reservations newReservation = reservationModel.createReservation(email);
                TicketType ticketTypeId = ticketTypeModel.getTicketTypeIdFromTitle(ticketTypeTitle);
                Event eventId = eventModel.getEventIdFromTitle(eventTitle);
                purchasedTicketsModel.createPurchasedTickets(newReservation.getId(), ticketTypeId.getId(), eventId.getId(), uniqueString, quantity);
            }

            if (selectedTicket != null) {
                // Subtract the quantity from the reservation from the quantityAvailable field
                int updatedQuantity = selectedTicket.getQuantityAvailable() - quantity;
                selectedTicket.setQuantityAvailable(updatedQuantity);

                // Update the ticket in the database with the new quantityAvailable value
                ticketModel.updateQuantityAvailable(selectedTicket); // Pass the updated ticket object
            }

            // Close the window
            mainViewController.setCenterView("/View/ReservationsView.fxml");
            mainViewController.lblMenuTitle.setText("Reservations");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save reservation and tickets.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
