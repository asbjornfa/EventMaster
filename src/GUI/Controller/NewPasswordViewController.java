package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewPasswordViewController {

    // Password input fields
    public MFXPasswordField txtFieldPassword;
    public MFXPasswordField txtFieldReEnterPassword;

    // UserModel instance to handle password update
    private UserModel userModel;
    // Current user's username
    private String currentUser;

    // Initialize the controller and set up the UserModel instance
    public NewPasswordViewController() throws SQLException {
        userModel = new UserModel();
    }

    // Set the current user's username
    public void setUser(String username){
        this.currentUser = username;
    }

    // Handle the save password button click event
    @FXML
    void onClickSavePassword(ActionEvent event) {
        // Get the new password and re-entered password
        String newPassword = txtFieldPassword.getText().trim();
        String reEnteredPassword = txtFieldReEnterPassword.getText().trim();

        // Check if the passwords match
        if (!newPassword.equals(reEnteredPassword)) {
            // Show an error alert if the passwords do not match
            showErrorAlert("Passwords Mismatch", "The entered passwords do not match.");
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
        if (!hasUppercase ||!hasLowercase ||!hasDigit ||!hasSpecialChar ||!isLongEnough) {
            showErrorAlert("Invalid Password", "Password does not meet the requirements", alertMessage.toString());
        } else {
            // If the password meets all requirements, update the password
            userModel.updatePassword(currentUser, newPassword);

            // Close the view
            ((Stage) txtFieldPassword.getScene().getWindow()).close();

            // Show success message
            showAlert("Success", "Password has been updated successfully.");
        }
    }

    // Show an error alert with the specified title and message
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Show an alert with the specified title, header, and message
    private void showErrorAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Show an alert with the specified title and message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}