package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewPasswordViewController implements Initializable {

    // Password input fields
    public MFXPasswordField txtFieldPassword;
    public MFXPasswordField txtFieldReEnterPassword;

    // Error labels
    public Label lblPasswordError;
    public Label lblReEnterPasswordError;

    // UserModel instance to handle password update
    private UserModel userModel;
    // Current user's username
    private String currentUser;

    private Stage loginStage;

    // Initialize the controller and set up the UserModel instance
    public NewPasswordViewController() throws SQLException {
        userModel = new UserModel();
    }

    // Set the current user's username
    public void setUser(String username){
        this.currentUser = username;
    }

    // Setter method for setting the login stage
    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Clear previous error messages and border colors
        clearErrorMessages();
        clearBorders();

        // Add listeners to reset border color when text fields are edited
        txtFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldPassword.setStyle("-fx-border-color: white;");
            lblPasswordError.setText("");
        });

        txtFieldReEnterPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldReEnterPassword.setStyle("-fx-border-color: white;");
            lblReEnterPasswordError.setText("");
        });
    }

    // Handle the save password button click event
    @FXML
    void onClickSavePassword(ActionEvent event) {
        // Get the new password and re-entered password
        String newPassword = txtFieldPassword.getText().trim();
        String reEnteredPassword = txtFieldReEnterPassword.getText().trim();

        // Clear previous error messages
        clearErrorMessages();

        // Check if the passwords match
        if (!newPassword.equals(reEnteredPassword)) {
            showErrorFeedback(txtFieldReEnterPassword, lblReEnterPasswordError, "Passwords do not match");
            return; // Exit method if passwords do not match
        }

        // Define the password requirements
        boolean hasUppercase = newPassword.matches(".*[A-Z].*");
        boolean hasLowercase = newPassword.matches(".*[a-z].*");
        boolean hasDigit = newPassword.matches(".*\\d.*");
        boolean hasSpecialChar = newPassword.matches(".*[@$!%*?&#].*");
        boolean isLongEnough = newPassword.length() >= 8;

        // Construct the alert message based on the validation results
        StringBuilder alertMessage = new StringBuilder("Password must:");
        if (!hasUppercase) {
            alertMessage.append("\n- contain at least one uppercase letter");
        }
        if (!hasLowercase) {
            alertMessage.append("\n- contain at least one lowercase letter");
        }
        if (!hasDigit) {
            alertMessage.append("\n- contain at least one digit");
        }
        if (!hasSpecialChar) {
            alertMessage.append("\n- contain at least one special character (@$!%*?&#)");
        }
        if (!isLongEnough) {
            alertMessage.append("\n- be at least 8 characters long");
        }

        // Show the alert with the constructed message if password doesn't meet requirements
        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecialChar || !isLongEnough) {
            showErrorFeedback(txtFieldPassword, lblPasswordError, alertMessage.toString());
        } else {
            // If the password meets all requirements, update the password
            userModel.updatePassword(currentUser, newPassword);

            // Close the view after 1 seconds
            Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), e -> {
                try {
                    navigateToLogin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }));
            timeline.play();
        }

            // Show success message
            lblPasswordError.setText("Password has been updated successfully.");
            lblPasswordError.setTextFill(Color.GREEN);
        }

    // Method to navigate back to the login screen
    private void navigateToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginView.fxml"));
        Parent root = loader.load();

        LoginViewController loginController = loader.getController();
        loginController.setStage(loginStage);

        loginStage.getScene().setRoot(root);
    }

    // Method to show error feedback
    private void showErrorFeedback(MFXPasswordField field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red;");
        errorLabel.setText(errorMessage);

        // Shake animation for text field
        shakeNode(field);
    }

    // Method to clear error messages and border colors
    private void clearErrorMessages() {
        lblPasswordError.setText("");
        lblReEnterPasswordError.setText("");
    }

    private void clearBorders() {
            txtFieldPassword.setStyle("-fx-border-color: white;");
            txtFieldReEnterPassword.setStyle("-fx-border-color: white;");
        }


    // Method to apply shake animation to a node
    private void shakeNode(MFXPasswordField node) {
        double originalTranslateX = node.getTranslateX();
        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(0), new KeyValue(node.translateXProperty(), 0)),
                new KeyFrame(javafx.util.Duration.seconds(0.1), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(javafx.util.Duration.seconds(0.2), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(javafx.util.Duration.seconds(0.3), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(javafx.util.Duration.seconds(0.4), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(javafx.util.Duration.seconds(0.5), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(javafx.util.Duration.seconds(0.6), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(javafx.util.Duration.seconds(0.7), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(javafx.util.Duration.seconds(0.8), new KeyValue(node.translateXProperty(), 10)),
                new KeyFrame(javafx.util.Duration.seconds(0.9), new KeyValue(node.translateXProperty(), -10)),
                new KeyFrame(javafx.util.Duration.seconds(1), new KeyValue(node.translateXProperty(), originalTranslateX))
        );
        timeline.play();
    }


}
