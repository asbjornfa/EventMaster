package GUI.Controller;

import BE.PurchasedTickets;
import GUI.Model.PurchasedTicketsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public ReservationsViewController() throws SQLException, IOException {
        purchasedTicketsModel = new PurchasedTicketsModel();
    }

    @FXML
    public void initialize() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colEmailString.setCellValueFactory(new PropertyValueFactory<>("emailString"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        colTicketType.setCellValueFactory(new PropertyValueFactory<>("ticketTypeTitle"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tblReservations.setItems(purchasedTicketsModel.getObservablePurchasedTickets());
    }

    public void handleDeleteReservation(ActionEvent event) {
    }


    public void handlePrintTicket(ActionEvent actionEvent) {
        PurchasedTickets selectedticket = tblReservations.getSelectionModel().getSelectedItem();

        if(selectedticket != null) {


            // Her kalder vi generatePdf med paneTicket som argument
            //pdfTicketController.generatePdf((AnchorPane) root);

        }

    }

}
