package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    // Buttons
    public Button cancelBtn;
    public Button logInBtn;

    // Input fields
    public MFXPasswordField enterPassword;
    public MFXTextField enterUsername;

    // UserModel instance
    private UserModel userModel;

    private Stage stage; // Reference to the login stage
    // Reference to the main controller
    private MainViewController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialize the UserModel instance
            userModel = new UserModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Setter method for setting the main controller
    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }


    // Set the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    // Handle the login button click event
    public void loginBtnHandle(ActionEvent actionEvent) throws IOException {
        // Get the username and password from the input fields
        String username = enterUsername.getText().trim();
        String password = enterPassword.getText().trim();

        // Authenticate the user using the UserModel instance
        User authenticatedUser = userModel.authenticateUser(username, password);

        // Check if the user is authenticated
        if (authenticatedUser != null) {

            if (authenticatedUser.getPassword() == null) {
                // User has no password, open the NewPasswordView
                openNewPasswordView(username);
            } else {

                // Set the logged-in username in UserModel
                userModel.setLoggedInUsername(username);
                stage.close();

                // Open the main view
                openMainView(authenticatedUser);

            }

        } else {
            // Authentication failed, show an error message
            showAlert("Login Failed", "Invalid username or password");
        }
    }


    // Open the NewPasswordView
    private void openNewPasswordView(String username) throws IOException {
        // Load the NewPasswordView FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPasswordView.fxml"));
        // Get the root of the NewPasswordView
        Parent root = loader.load();
        // Get the NewPasswordViewController
        NewPasswordViewController newPasswordController = loader.getController();
        // Set the username to the NewPasswordViewController
        newPasswordController.setUser(username);

        // Create a new stage
        Stage stage = new Stage();
        // Set the scene of the stage
        stage.setScene(new Scene(root));

        // Set the stage properties
        stage.setResizable(false); // Make the stage not resizable
        stage.centerOnScreen(); // Center the stage on the screen

        // Show the stage
        stage.show();
    }

    private void openMainView(User authenticatedUser) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
        Parent root = loader.load();

        MainViewController mainController = loader.getController();
        mainController.setUser(authenticatedUser);

        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(root));
        mainStage.setResizable(true);
        mainStage.centerOnScreen();
        mainStage.show();
    }


    // Show an alert message
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Set the alert title
        alert.setTitle(title);
        // Set the alert content
        alert.setContentText(content);
        // Show the alert and wait for it to be closed
        alert.showAndWait();
    }

    // Handle the cancel button click event
    public void cancelBtnHandle(ActionEvent event) {

        mainController.reopenHomepage();

        // Close the login stage
        stage.close();
    }
}