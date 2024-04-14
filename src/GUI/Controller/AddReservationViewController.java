package GUI.Controller;

import GUI.Model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddReservationViewController implements Initializable {

    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblTicketType;

    @FXML
    private Label lblEventTitle;


    @FXML
    private TextField txtFieldCostumerEmail;



    private String eventTitle;

    private TicketModel ticketModel;

    public AddReservationViewController() throws SQLException, IOException {
        ticketModel = new TicketModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEventData(String eventTitle, String ticketTypeTitle) {
        lblEventTitle.setText(eventTitle);
        lblTicketType.setText(ticketTypeTitle);
    }

    @FXML
    public void onClickCancel(ActionEvent event) {

    }

    @FXML
    public void onClickSave(ActionEvent event) {
        String email = txtFieldCostumerEmail.getText(); // Get customer email from input field
        String eventTitle = lblEventTitle.getText(); // Get event title from label
        String ticketTypeTitle = lblTicketType.getText();

    }
}
