package GUI.Controller;

import BE.Event;
import BE.Reservations;
import BE.TicketType;
import BLL.ReservationManager;
import GUI.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    public AddReservationViewController() throws Exception {
        ticketModel = new TicketModel();
        reservationModel = new ReservationModel();
        purchasedTicketsModel = new PurchasedTicketsModel();
        ticketTypeModel = new TicketTypeModel();
        eventModel = new EventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEventData(String eventTitle, String ticketTypeTitle) {
        lblEventTitle.setText(eventTitle);
        lblTicketType.setText(ticketTypeTitle);
    }

    @FXML
    public void onClickCancel(ActionEvent event) {

    }

    @FXML
    public void onClickSave(ActionEvent event) {
        String email = txtFieldCostumerEmail.getText(); // Get customer email from input field
        String eventTitle = lblEventTitle.getText(); // Get event title from label
        String ticketTypeTitle = lblTicketType.getText();
        int quantity = Integer.parseInt(lblQuantity.getText());

        try {
            // Create the reservation and immediately retrieve the created reservation object
            Reservations newReservation = reservationModel.createReservation(email);

            TicketType ticketTypeId = ticketTypeModel.getTicketTypeIdFromTitle(ticketTypeTitle);


            Event eventId = eventModel.getEventIdFromTitle(eventTitle);


            // Now use the reservation ID from the newReservation object
            purchasedTicketsModel.createPurchasedTickets(newReservation.getId(), ticketTypeId.getId(), eventId.getId(), "Not in use yet", quantity);

            // Close the window
            ((Stage) lblQuantity.getScene().getWindow()).close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save reservation and tickets.", e);
        }
    }
}
