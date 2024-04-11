package GUI.Controller;

import BE.Reservations;
import BE.User;
import BLL.ReservationManager;
import GUI.Model.ReservationModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationsViewController implements Initializable {

    @FXML
    private TableView<Reservations> tblReservations;
    @FXML
    private TableColumn<Reservations, String> colName;
    @FXML
    private TableColumn<Reservations, String> colEmail;
    @FXML
    private TableColumn<Reservations, String> colEvent;

    @FXML
    private TableColumn colTicketType;

    @FXML
    private TableColumn colQuantity;


    private ReservationManager reservationManager;
    private ReservationModel reservationModel;

    public ReservationsViewController() throws Exception {
        try {
            reservationManager = new ReservationManager();
            reservationModel = new ReservationModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colName.setCellValueFactory(cellData -> {
            String firstName = cellData.getValue().getFirstName();
            String lastName = cellData.getValue().getLastName();
            return new SimpleStringProperty(firstName + " " + lastName);
        });

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblReservations.setItems(reservationModel.getObservableReservations());

    }


    public void handleDeleteReservation(ActionEvent actionEvent) throws IOException {
        Reservations selectedReservation = tblReservations.getSelectionModel().getSelectedItem();

        if(selectedReservation != null) {
            reservationModel.deleteReservation(selectedReservation);
            reservationModel.getObservableReservations().remove(selectedReservation);

            clearSelection();
        }
    }

    public void clearSelection() {

        tblReservations.getSelectionModel().clearSelection();
    }
}
