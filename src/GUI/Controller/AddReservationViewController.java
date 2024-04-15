package GUI.Controller;

import BE.Event;
import BE.Reservations;
import BE.Ticket;
import BE.TicketType;
import BLL.EventManager;
import BLL.ReservationManager;
import GUI.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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

    public void setSelectedTicket(Ticket ticket) {
        this.selectedTicket = ticket;
    }

    public void setEventData(String eventTitle, String ticketTypeTitle) {
        lblEventTitle.setText(eventTitle);
        lblTicketType.setText(ticketTypeTitle);
    }


    @FXML
    public void onClickCancel(ActionEvent event) {
        // Get the reference to the cancel button's stage
        Stage stage = (Stage) lblQuantity.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    @FXML
    public void onClickSave(ActionEvent event) {
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

        try {
            // Check if email already exists in the database
            Reservations existingReservation = reservationManager.getReservationByEmail(email);
            if (existingReservation != null) {
                // Use the existing reservation ID
                TicketType ticketTypeId = ticketTypeModel.getTicketTypeIdFromTitle(ticketTypeTitle);
                Event eventId = eventModel.getEventIdFromTitle(eventTitle);
                purchasedTicketsModel.createPurchasedTickets(existingReservation.getId(), ticketTypeId.getId(), eventId.getId(), "Not in use yet", quantity);
            } else {
                // Create a new reservation
                Reservations newReservation = reservationModel.createReservation(email);
                TicketType ticketTypeId = ticketTypeModel.getTicketTypeIdFromTitle(ticketTypeTitle);
                Event eventId = eventModel.getEventIdFromTitle(eventTitle);
                purchasedTicketsModel.createPurchasedTickets(newReservation.getId(), ticketTypeId.getId(), eventId.getId(), "Not in use yet", quantity);
            }

            //Not working 
            if (selectedTicket != null) {
                // Subtract the quantity from the reservation from the quantityAvailable field
                int updatedQuantity = selectedTicket.getQuantityAvailable() - quantity;
                selectedTicket.setQuantityAvailable(updatedQuantity);

                // Update the ticket in the database with the new quantityAvailable value
                ticketModel.updateTicket(selectedTicket); // Pass the updated ticket object
            }


            // Close the window
            ((Stage) lblQuantity.getScene().getWindow()).close();

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
