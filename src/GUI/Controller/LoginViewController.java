package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private BorderPane loginBorderPane;
    @FXML
    private Button logInBtn;
    @FXML
    private MFXPasswordField enterPassword;
    @FXML
    private MFXTextField enterUsername;


    private UserModel userModel;

    private Stage stage;

    private MainViewController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userModel = new UserModel();

            // Add event listeners to reset border color when text fields are edited
            enterUsername.textProperty().addListener((observable, oldValue, newValue) -> {
                enterUsername.setStyle("-fx-border-color: white;");
            });
            enterPassword.textProperty().addListener((observable, oldValue, newValue) -> {
                enterPassword.setStyle("-fx-border-color: white;");
            });

            //Allows the user to press loginbtn with enter
            enterPassword.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        loginBtnHandle(new ActionEvent());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
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
            showErrorFeedback();
        }
    }

    // Method to show error feedback
    private void showErrorFeedback() {
        enterUsername.setStyle("-fx-border-color: red;");
        enterPassword.setStyle("-fx-border-color: red;");
        // Shake animation for text fields
        shakeNode(enterUsername);
        shakeNode(enterPassword);
    }

    // Method to apply shake animation to a node
    private void shakeNode(Node node) {
        // Get the original translation of the node
        double originalTranslateX = node.getTranslateX();

        // Define a Timeline for the animation with a series of KeyFrames
        Timeline timeline = new Timeline(
                // Start KeyFrame: Set translation to 0
                new KeyFrame(Duration.seconds(0), new KeyValue(node.translateXProperty(), 0)),
                // Subsequent KeyFrames: Alternating translations to create a shaking effect
                new KeyFrame(Duration.seconds(0.1), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.seconds(0.6), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(Duration.seconds(0.9), new KeyValue(node.translateXProperty(), -10)),
                // Final KeyFrame: Restore the original translation
                new KeyFrame(Duration.seconds(1), new KeyValue(node.translateXProperty(), originalTranslateX))
        );
        // Play the animation
        timeline.play();
    }

    private void openNewPasswordView(String username) throws IOException {
        // Load the NewPasswordView FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPasswordView.fxml"));
        // Get the root of the NewPasswordView
        Parent root = loader.load();
        // Get the NewPasswordViewController
        NewPasswordViewController newPasswordController = loader.getController();
        // Set the username to the NewPasswordViewController
        newPasswordController.setUser(username);
        // Pass the login stage reference to the NewPasswordViewController
        newPasswordController.setLoginStage(stage);

        // Add the NewPasswordView to the center of the login screen's BorderPane
        loginBorderPane.setCenter(root);
    }

    private void openMainView(User authenticatedUser) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
        Parent root = loader.load();

        MainViewController mainController = loader.getController();
        mainController.setUser(authenticatedUser);
        mainController.setLoginViewController(this);

        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(root));
        mainStage.setResizable(true);
        mainStage.centerOnScreen();
        mainStage.setTitle("EASV");
        mainStage.show();
    }
}
