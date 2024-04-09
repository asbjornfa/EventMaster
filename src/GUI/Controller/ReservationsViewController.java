package GUI.Controller;

import BE.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationsViewController implements Initializable {

    @FXML
    private TableView tblReservations;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colEvent;
    @FXML
    private TableColumn colTickets;
    @FXML
    private TableColumn colStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }
}
