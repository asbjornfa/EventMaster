package GUI.Controller;

import BE.Ticket;
import BE.TicketType;
import GUI.Model.TicketModel;
import GUI.Model.TicketTypeModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateTicketViewController implements Initializable {

    @FXML
    private Label eventName;

    @FXML
    private MenuButton ticketTypeDropDown;

    @FXML
    private TextField txtFieldPrice;

    @FXML
    private TextField txtFieldQuantity;

    private String eventTitle;
    private int eventId;
    private int ticketTypeId;
    private TicketTypeModel ticketTypeModel;
    private TicketModel ticketModel;

    public CreateTicketViewController() throws SQLException, IOException {
        ticketTypeModel = new TicketTypeModel();
        ticketModel = new TicketModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEventTitle(eventTitle);
        populateTicketTypeDropDown();

    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        eventName.setText(eventTitle);
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    @FXML
    public void onClickCancel(ActionEvent event) {

    }

    @FXML
    public void onClickSave(ActionEvent event) {
        // Get the values from the input fields
        int price = Integer.parseInt(txtFieldPrice.getText());
        int quantityAvailable = Integer.parseInt(txtFieldQuantity.getText());

        try {
            // Create the ticket in the database
            ticketModel.createTicket(price, ticketTypeId, eventId, quantityAvailable);


            ((Stage) txtFieldQuantity.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickTicketTypeDropDown(ActionEvent event) {

    }

    private void populateTicketTypeDropDown() {
        ObservableList<TicketType> ticketTypes = ticketTypeModel.getObservableTicketType();
        ticketTypeDropDown.getItems().clear();
        for (TicketType ticketType : ticketTypes) {
            MenuItem menuItem = new MenuItem(ticketType.getTitle());
            menuItem.setOnAction(event -> handleTicketTypeSelection(ticketType));
            ticketTypeDropDown.getItems().add(menuItem);
        }
    }

    private void handleTicketTypeSelection(TicketType ticketType) {
        // Store the selected ticket type's ID
        ticketTypeId = ticketType.getId();
        ticketTypeDropDown.setText(ticketType.getTitle());
    }


}
