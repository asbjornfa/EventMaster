package GUI.Controller;

import BE.Event;
import BE.Ticket;
import BE.TicketType;
import DAL.DB.TicketDAO_DB;
import GUI.Model.TicketModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

public class TicketViewTableController implements Initializable, MainViewControllerAware {

    public Button btnAddReservation;
    public Button btnEditEventTickets;
    public Button btnDeleteEventTickets;
    @FXML
    private TableColumn<Ticket, String> colEventName;
    @FXML
    private TableColumn<Ticket, Integer> colPrice;
    @FXML
    private TableColumn<Ticket, Integer> colTicketAvailable;
    @FXML
    private TableColumn<Ticket, String> colTicketType;

    @FXML
    private TableView<Ticket> tblViewEventTickets;

    private TicketModel ticketModel;

    private MainViewController mainViewController;

    public TicketViewTableController() throws SQLException, IOException {
        ticketModel = new TicketModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTicketType.setCellValueFactory(new PropertyValueFactory<>("ticketTypeTitle"));
        colTicketAvailable.setCellValueFactory(new PropertyValueFactory<>("quantityAvailable"));

        tblViewEventTickets.setItems(ticketModel.getObservableTicket());

        // Disable the button by default
        btnAddReservation.setDisable(true);
        btnEditEventTickets.setDisable(true);
        btnDeleteEventTickets.setDisable(true);


        // Add a listener to the TableView's selection model
        tblViewEventTickets.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Enable the button if a row is selected, otherwise disable
            btnAddReservation.setDisable(newSelection == null);
            btnEditEventTickets.setDisable(newSelection == null);
            btnDeleteEventTickets.setDisable(newSelection == null);
        });
    }


    // Method to set MainViewController
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @FXML
    void onClickAddReservation(ActionEvent event) throws IOException {
        Ticket selectedTicketEvent = tblViewEventTickets.getSelectionModel().getSelectedItem();

        if (selectedTicketEvent != null) {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddReservationView.fxml"));
            Parent root = loader.load();

            // Access the controller
            AddReservationViewController addReservationController = loader.getController();

            // Pass event and ticket type titles to the controller
            addReservationController.setEventData(selectedTicketEvent.getEventTitle(), selectedTicketEvent.getTicketTypeTitle());

            addReservationController.setMainViewController(mainViewController);

            mainViewController.setCenterView(root); // Adjust this line if you use different method to change views
            mainViewController.lblMenuTitle.setText("Add reservation");
        }
    }


    @FXML
    private void onClickDeleteTicket(ActionEvent event) throws IOException {
        Ticket selected = tblViewEventTickets.getSelectionModel().getSelectedItem();
        if (selected != null) {

            ticketModel.deleteTicket(selected);

        }
    }

    @FXML
    void onClickEditTicket(ActionEvent event) {
        //Needs to be made
    }

}
