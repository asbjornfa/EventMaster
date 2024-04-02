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

    // Declare a UserModel object to interact with the database
    private UserModel userModel;

    // Declare TextField objects for each input field
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

    // Initialize method called when the controller is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialize the UserModel object
            userModel = new UserModel();
        } catch (SQLException e) {
            // Throw a runtime exception if there is an error initializing the UserModel
            throw new RuntimeException(e);
        }
    }

    // Method called when the "Create User" button is clicked
    @FXML
    void onClickBtnCreateUser(ActionEvent event) {
        // Get the values from the input fields
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String fullName = firstName + " " + lastName;
        String email = txtFieldEmail.getText();
        String username = txtFieldUsername.getText();

        // Initialize a StringBuilder object to store error messages
        StringBuilder errorMessage = new StringBuilder();

        // Check if all the required fields are filled in
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

            // Create the user in the database
            userModel.createUser(fullName, email, username);

            // Show a success alert
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("User Created");
            successAlert.setHeaderText(null);
            successAlert.setContentText("User has been created successfully.");
            successAlert.showAndWait();

            // Clear the input fields
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
        // Regular expression pattern for a valid email address
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        // Test the email against the regex pattern and return the result
        return email.matches(regex);
    }

}