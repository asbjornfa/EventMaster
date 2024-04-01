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
        String password = txtFieldPassword.getText();
        String username = txtFieldUsername.getText();

        if (!firstName.isEmpty() && !email.isEmpty() && !username.isEmpty()) {
            User user = new User(fullName, email, password, username);

            userModel.createUser(fullName, email, password, username);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("User Created");
            successAlert.setHeaderText(null);
            successAlert.setContentText("User has been created successfully.");
            successAlert.showAndWait();

            txtFieldFirstName.clear();
            txtFieldLastName.clear();
            txtFieldEmail.clear();
            txtFieldPassword.clear();
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

}


