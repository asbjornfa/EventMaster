package GUI.Controller;

import BE.Ticket;
import BE.TicketType;
import GUI.Model.TicketModel;
import GUI.Model.TicketTypeModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateTicketViewController implements Initializable {

    public MFXButton btnSave;
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
    private MainViewController mainViewController;
    private Ticket ticketToEdit;

    public CreateTicketViewController() throws SQLException, IOException {
        ticketTypeModel = new TicketTypeModel();
        ticketModel = new TicketModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEventTitle(eventTitle);
        populateTicketTypeDropDown();
    }

    // Method to set MainViewController
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        eventName.setText(eventTitle);
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setTicketToEdit(Ticket ticketToEdit) {
        this.ticketToEdit = ticketToEdit;
        // Populate UI fields with the data from ticketToEdit
        setTicketInformation(ticketToEdit);
    }

    private void setTicketInformation(Ticket ticket) {
        if(ticket != null){
            eventName.setText(ticket.getEventTitle());
            ticketTypeDropDown.setText(ticket.getTicketTypeTitle());
            txtFieldPrice.setText(String.valueOf(ticket.getPrice()));
            txtFieldQuantity.setText(String.valueOf(ticket.getQuantityAvailable()));

            btnSave.setText("Update tickets");
            btnSave.setStyle("-fx-background-color: #FBBB2C");
        }
    }

    @FXML
    public void onClickCancel(ActionEvent event) throws IOException {
        mainViewController.setCenterView("/View/ActiveEvent.fxml");
        mainViewController.lblMenuTitle.setText("Events");
    }

    @FXML
    public void onClickSave(ActionEvent event) {
        // Get the values from the input fields
        int price = Integer.parseInt(txtFieldPrice.getText());
        int quantityAvailable = Integer.parseInt(txtFieldQuantity.getText());

        try {
            if (ticketToEdit != null) {
                // Update the ticket in the database
                ticketToEdit.setPrice(price);
                ticketToEdit.setQuantityAvailable(quantityAvailable);
                ticketToEdit.setTicketTypeId(ticketTypeId);
                ticketModel.updateTicket(ticketToEdit);
            } else {
                // Create the ticket in the database
                ticketModel.createTicket(price, ticketTypeId, eventId, quantityAvailable);
            }

            mainViewController.setCenterView("/View/TicketViewTable.fxml");
            mainViewController.lblMenuTitle.setText("Event tickets");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onClickTicketTypeDropDown(ActionEvent event) {
        // Handle ticket type selection
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
        ticketTypeId = ticketType.getId();  // Ensure this is getting the correct ID
        ticketTypeDropDown.setText(ticketType.getTitle());

    }
}
