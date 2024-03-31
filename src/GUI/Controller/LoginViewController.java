package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    public Button cancelBtn;
    public Button logInBtn;
    public TextField enterPassword;
    public TextField enterUsername;

    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userModel = new UserModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginBtnHandle(ActionEvent actionEvent) throws IOException {
        String username = enterUsername.getText().trim();
        String password = enterPassword.getText().trim();

            User authenticatedUser = userModel.authenticateUser(username, password);

            if (authenticatedUser != null) {
                // User authenticated successfully
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Login Completed");
                alert.setHeaderText(null); // No header
                alert.setContentText("Welcome");

                alert.showAndWait(); // Show the dialog and wait for it to be closed

            } else {
                // Authentication failed, show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null); // No header
                alert.setContentText("Invalid username or password");

                alert.showAndWait(); // Show the dialog and wait for it to be closed
            }
    }



    public void cancelBtnHandle(ActionEvent event) {

    }
}
