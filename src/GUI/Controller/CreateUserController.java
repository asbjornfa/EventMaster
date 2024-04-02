package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {

    private UserModel userModel;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldUsername;

    public CreateUserController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userModel = new UserModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickBtnCreateUser(ActionEvent event) {
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String fullName = firstName + " " + lastName;
        String email = txtFieldEmail.getText();
        String username = txtFieldUsername.getText();

        StringBuilder errorMessage = new StringBuilder();

        if (!firstName.isEmpty() && !email.isEmpty() && !username.isEmpty()) {
            // Check if the username already exists
            if (userModel.usernameExists(username)) {
                errorMessage.append("   - " + "The username already exists.\n\n");
            }

            // Check if the email already exists
            if (userModel.emailExists(email)) {
                errorMessage.append("   - " + "The email already exists.\n\n");
            }

            // Check if the email is in a valid format
            if (!isValidEmail(email)) {
                errorMessage.append("   - " + "Please enter a valid email address.\n\n");
            }

            // If there are any error messages, display them
            if (errorMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("User Creation Failed");
                alert.setHeaderText(null);
                alert.setContentText(errorMessage.toString());
                alert.showAndWait();
                return; // Exit the method if there are errors
            }

            userModel.createUser(fullName, email, username);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("User Created");
            successAlert.setHeaderText(null);
            successAlert.setContentText("User has been created successfully.");
            successAlert.showAndWait();

            txtFieldFirstName.clear();
            txtFieldLastName.clear();
            txtFieldEmail.clear();
            txtFieldUsername.clear();

        } else {
            // Show an alert indicating that all fields are required
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();
        }
    }

    // Method to validate email format using regex
    private boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

}


