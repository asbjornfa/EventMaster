package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddReservationViewController implements Initializable {

    public Label labelEvent;
    @FXML
    private MenuButton ticketTypeDropDown;

    @FXML
    private TextField txtFieldCostumerEmail;

    @FXML
    private TextField txtFieldQuantity;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void onClickCancel(ActionEvent event) {

    }

    @FXML
    public void onClickSave(ActionEvent event) {

    }

    @FXML
    public void onClickTicketTypeDropDown(ActionEvent event) {

    }


}
