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
            if (authenticatedUser.getPassword() == null) {
                // User has no password, open NewPasswordView
                openNewPasswordView(username);
            } else {
                // User has a password, show login completed message
                showAlert("Login Completed", "Welcome");
            }
        } else {
            // Authentication failed, show error message
            showAlert("Login Failed", "Invalid username or password");
        }
    }

    private void openNewPasswordView(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPasswordView.fxml"));
        Parent root = loader.load();
        NewPasswordViewController newPasswordController = loader.getController();
        newPasswordController.setUser(username); // Pass the username to the NewPasswordViewController

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        // Set stage properties
        stage.setResizable(false); // Make the stage not resizable
        stage.centerOnScreen(); // Center the stage on the screen

        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header
        alert.setContentText(content);
        alert.showAndWait(); // Show the dialog and wait for it to be closed
    }


    public void cancelBtnHandle(ActionEvent event) {

    }
}
