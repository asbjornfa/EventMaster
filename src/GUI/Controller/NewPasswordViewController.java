package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import java.sql.SQLException;

public class NewPasswordViewController {

    public TextField txtFieldPassword;

    private UserModel userModel;
    private User currentUser;

    public NewPasswordViewController() throws SQLException {
        userModel = new UserModel();
    }

    public void setUser(User user){
        this.currentUser = user;
    }

    @FXML
    void onClickSavePassword(ActionEvent event) {
        String newPassword = txtFieldPassword.getText().trim();

        // Define the password requirements
        boolean hasUppercase = newPassword.matches(".*[A-Z].*");
        boolean hasLowercase = newPassword.matches(".*[a-z].*");
        boolean hasDigit = newPassword.matches(".*\\d.*");
        boolean hasSpecialChar = newPassword.matches(".*[@$!%*?&].*");
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
            alertMessage.append("\n- contain at least one special character (@$!%*?&)");
        }
        if (!isLongEnough) {
            alertMessage.append("\n- be at least 8 characters long");
        }

        // Show the alert with the constructed message
        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecialChar || !isLongEnough) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Password");
            alert.setHeaderText("Password does not meet the requirements");
            alert.setContentText(alertMessage.toString());
            alert.showAndWait();
        } else {
            // If the password meets all requirements, update the password
            userModel.updatePassword(currentUser.getUsername(), newPassword);
        }
    }

}

