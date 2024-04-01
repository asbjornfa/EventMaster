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

    public MFXPasswordField txtFieldPassword;
    public MFXPasswordField txtFieldReEnterPassword;

    private UserModel userModel;
    private String currentUser;

    public NewPasswordViewController() throws SQLException {
        userModel = new UserModel();
    }

    public void setUser(String username){
        this.currentUser = username;
    }

    @FXML
    void onClickSavePassword(ActionEvent event) {
        String newPassword = txtFieldPassword.getText().trim();
        String reEnteredPassword = txtFieldReEnterPassword.getText().trim();

        // Check if passwords match
        if (!newPassword.equals(reEnteredPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Passwords Mismatch");
            alert.setHeaderText(null);
            alert.setContentText("The entered passwords do not match.");
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Password");
            alert.setHeaderText("Password does not meet the requirements");
            alert.setContentText(alertMessage.toString());
            alert.showAndWait();
        } else {
            // If the password meets all requirements, update the password
            userModel.updatePassword(currentUser, newPassword);

            // Close the view
            ((Stage) txtFieldPassword.getScene().getWindow()).close();

            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Password has been updated successfully.");
            successAlert.showAndWait();
        }
    }

}

