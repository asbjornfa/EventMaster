package GUI.Controller;

import BE.PurchasedTickets;
import BE.Reservations;
import GUI.Model.PurchasedTicketsModel;
import GUI.Model.ReservationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationsViewController implements Initializable {

    @FXML
    private Button btnDeleteReservation;
    @FXML
    private TableColumn<PurchasedTickets, String> colEmailString;
    @FXML
    private TableColumn<PurchasedTickets, String> colEventName;
    @FXML
    private TableColumn<PurchasedTickets, String> colTicketType;
    @FXML
    private TableColumn<PurchasedTickets, Integer> colQuantity;
    @FXML
    private TableView<PurchasedTickets> tblReservations;

    private PurchasedTicketsModel purchasedTicketsModel;
    private ReservationModel reservationModel;

    public ReservationsViewController() throws Exception {
        purchasedTicketsModel = new PurchasedTicketsModel();
        reservationModel = new ReservationModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colEmailString.setCellValueFactory(new PropertyValueFactory<>("emailString"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        colTicketType.setCellValueFactory(new PropertyValueFactory<>("ticketTypeTitle"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tblReservations.setItems(purchasedTicketsModel.getObservablePurchasedTickets());

        btnDeleteReservation.setDisable(true);

        // Add a listener to the TableView's selection model
        tblReservations.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Enable the button if a row is selected, otherwise disable
            btnDeleteReservation.setDisable(newSelection == null);
        });
    }

        public void handleDeleteReservation(ActionEvent event) throws IOException {
            PurchasedTickets selectedTicket = tblReservations.getSelectionModel().getSelectedItem();

            if (selectedTicket != null) {
                purchasedTicketsModel.deletePurchasedTickets(selectedTicket);
            }
        }
}
