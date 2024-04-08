package GUI.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationsViewController implements Initializable {

    public TableView tblReservations;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colEvent;
    public TableColumn colTickets;
    public TableColumn colStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
