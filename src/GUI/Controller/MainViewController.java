package GUI.Controller;

import GUI.Model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public MenuItem menuAuthorisedLogin;
    public MenuItem menuHome;
    public Button loginBtn;
    public BorderPane mainBorderPane;
    private Node OriginalCenter;
    private EventModel eventModel;

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OriginalCenter = mainBorderPane.getCenter();
    }

    public void handleAuthorisedLogin(ActionEvent actionEvent) throws Exception {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/loginView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Login");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load loginView.fxml");
                alert.showAndWait();
            }

        }

    public void handleHome(ActionEvent actionEvent) {
        mainBorderPane.setCenter(OriginalCenter);

    }

    public void createEventHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateEventView.fxml"));
        AnchorPane testView = loader.load();
        CreateEventController createEventView = loader.getController();
        createEventView.setEventModel(eventModel);
        mainBorderPane.setCenter(testView);
    }

    public void usersHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane testView = FXMLLoader.load((getClass().getResource("/View/UsersView.fxml")));
        mainBorderPane.setCenter(testView);
    }

    public void createUserHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane testView = FXMLLoader.load((getClass().getResource("/View/CreateUserView.fxml")));
        mainBorderPane.setCenter(testView);
    }

    public void eventsHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane testView = FXMLLoader.load((getClass().getResource("/View/ActiveEvent.fxml")));
        mainBorderPane.setCenter(testView);
    }


    public void pendingEventsHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane testView = FXMLLoader.load((getClass().getResource("/View/PendingEvents.fxml")));
        mainBorderPane.setCenter(testView);
    }
}