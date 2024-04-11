package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReservationsViewController {

    @FXML
    private TableColumn<?, ?> colCostumerEmail;

    @FXML
    private TableColumn<?, ?> colEventName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTicketType;

    @FXML
    private TableView<?> tblReservations;

    @FXML
    void handleDeleteReservation(ActionEvent event) {

    }

}
