package GUI.Controller;

import BE.Event;
import BE.Ticket;
import BE.TicketType;
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
import java.util.ResourceBundle;

public class TicketViewTableController implements Initializable {

    @FXML
    private TableColumn colEventName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colTicketAvailable;

    @FXML
    private TableColumn colTicketType;

    @FXML
    private TableView<Ticket> tblViewEventTickets;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onClickAddReservation(ActionEvent event) {

    }

    @FXML
    private void onClickCreateTicket(ActionEvent event) {
        try {
            // Load the FXML file for CreateTicketView
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateTicketView.fxml"));
            Parent root = loader.load();

            // Create the scene
            Scene scene = new Scene(root);

            // Create a new stage for the CreateTicketView
            Stage createTicketStage = new Stage();
            createTicketStage.setScene(scene);
            createTicketStage.setTitle("Create Ticket");
            createTicketStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickDeleteTicket(ActionEvent event) {

    }

    @FXML
    void onClickEditTicket(ActionEvent event) {

    }

}
