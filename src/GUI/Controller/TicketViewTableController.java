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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

public class TicketViewTableController implements Initializable {

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
    }

    @FXML
    void onClickAddReservation(ActionEvent event) throws IOException {

        Ticket selectedTicketEvent = tblViewEventTickets.getSelectionModel().getSelectedItem();

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddReservationView.fxml"));
        Parent root = loader.load();


        // Create a new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Reservation");

        // Show the stage
        stage.show();

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

    }

}
