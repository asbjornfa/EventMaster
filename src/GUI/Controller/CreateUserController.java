package GUI.Controller;

import BE.Positions;
import BE.User;
import BLL.PositionManager;
import GUI.Model.PositionModel;
import GUI.Model.UserModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {


    // Declare a UserModel object to interact with the database
    private UserModel userModel;
    private PositionModel positionModel;
    private MainViewController mainViewController;

    private Positions selectedPosition;

    // Declare TextField objects for each input field
    @FXML
    private Button btnCreateUserOrUpdate;

    @FXML
    private MenuButton menuButton;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldUsername;

    // This user will be null when creating a new user and contain a user object when editing an existing user.
    private User editingUser;

    // Initialize method called when the controller is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialize the UserModel object
            userModel = new UserModel();
            positionModel = new PositionModel();

            loadPositionsIntoMenuButton();
        } catch (SQLException e) {
            // Throw a runtime exception if there is an error initializing the UserModel
            throw new RuntimeException(e);
        }
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    // Call this method when you want to edit an existing user.
    public void setUserForEditing(User user) {
        this.editingUser = user;
        setUserInformation(user);
    }

    @FXML
    void onClickBtnCreateUser(ActionEvent event) throws IOException {

        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText();
        String username = txtFieldUsername.getText();
        String position = menuButton.getText();

        if(editingUser == null) {
            // Creating a new user

            // Validate input fields
            if (!validateFields(firstName, lastName, email, username, position)) {
                return;
            }

            // Create the user in the database
            userModel.createUser(firstName + " " + lastName, email, username, selectedPosition.getId());

            // Show success alert
            showAlert(Alert.AlertType.INFORMATION, "User Created", "User has been created successfully.");
        }else {
            editingUser.setName(firstName + " " + lastName);
            editingUser.setEmail(email);
            editingUser.setUsername(username);
            editingUser.setPositionId(selectedPosition.getId());
            userModel.updateUser(editingUser);

            // Show success alert
            showAlert(Alert.AlertType.INFORMATION, "User updated", "User has been updated successfully.");

        }


        // Clear the input fields
        clearFields();
    }

    // Method to set user information into the input fields for update
    public void setUserInformation(User user) {
        if (user != null) {
            String fullName = user.getName();
            // Split the full name into first name and last name
            String[] parts = fullName.split(" ", 2);
            if (parts.length == 2) {
                txtFieldFirstName.setText(parts[0]); // First name
                txtFieldLastName.setText(parts[1]);  // Last name
            }
            txtFieldEmail.setText(user.getEmail());
            txtFieldUsername.setText(user.getUsername());

            // Set the selected position
            String positionName = user.getPosition();
            menuButton.setText(positionName);

            // Change the button text and color to indicate update
            btnCreateUserOrUpdate.setText("Update user");
            btnCreateUserOrUpdate.setStyle("-fx-background-color: #FBBB2C;");
        }
    }

    // Method to show an alert
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Method to validate input fields
    private boolean validateFields(String firstName, String lastName, String email, String username, String position) {
        StringBuilder errorMessage = new StringBuilder();

        // Check if all the required fields are filled in
        if (firstName.isEmpty() || email.isEmpty() || username.isEmpty() || position.isEmpty()) {
            errorMessage.append("   - " + "Please fill in all the required fields.\n\n");
        }

        // Check if the email is in a valid format using regex
        if (!isValidEmail(email)) {
            errorMessage.append("   - " + "Please enter a valid email address.\n\n");
        }

        // Check if the username already exists
        if (userModel.usernameExists(username)) {
            errorMessage.append("   - " + "The username already exists.\n\n");
        }

        // Check if the email already exists
        if (userModel.emailExists(email)) {
            errorMessage.append("   - " + "The email already exists.\n\n");
        }

        // If there are any error messages, display them
        if (errorMessage.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("User Creation Failed");
            alert.setHeaderText(null);
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false; // Return false to indicate validation failure
        }

        return true; // Return true if all validations pass
    }

    // Method to validate email format using regex
    private boolean isValidEmail(String email) {
        // Regular expression pattern for a valid email address
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        // Test the email against the regex pattern and return the result
        return email.matches(regex);
    }

    // Method to clear input fields
    private void clearFields() {
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        txtFieldUsername.clear();
        String menubuttonText = null;
        menuButton.setText(menubuttonText);
    }

    // Method to handle position selection
    private void handlePositionSelection(Positions position) {
        selectedPosition = position;
        menuButton.setText(position.getPosition()); // Set the MenuButton text to the selected position's name.
    }

    private void loadPositionsIntoMenuButton() {
        ObservableList<Positions> positions = positionModel.getObservablePositions();
        for (Positions position : positions) {
            String positionName = position.getPosition(); // Assuming getPosition() returns the name.
            MenuItem item = new MenuItem(positionName);
            item.setOnAction(event -> {
                handlePositionSelection(position); // Call handlePositionSelection method when position is selected.
            });
            menuButton.getItems().add(item);
        }
    }

}

