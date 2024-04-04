package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersViewController implements Initializable {

    private UserModel userModel;
    private MainViewController mainViewController;

    @FXML
    private Button btnCreateUser;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPosition;

    @FXML
    private TableColumn colUsername;

    @FXML
    private TableView<User> tblViewUsers;

    public UsersViewController() throws SQLException {
        userModel = new UserModel();
    }

    // Method to set MainViewController
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

        tblViewUsers.setItems(userModel.getObservableUsers());
    }

    public void clearSelection() {
        tblViewUsers.getSelectionModel().clearSelection();
    }

    @FXML
    void onClickDeleteUser(ActionEvent event) {
        User selectedUser = tblViewUsers.getSelectionModel().getSelectedItem();

        if(selectedUser != null){
            userModel.deleteUser(selectedUser);
            userModel.getObservableUsers().remove(selectedUser);

            clearSelection();
        }
        else {
            btnDeleteUser.setDisable(true);
        }
    }

    @FXML
    void onClickCreateUser(ActionEvent event) throws IOException {
        if (tblViewUsers.getSelectionModel().getSelectedItem() == null) {
            changeScene("/View/CreateUserView.fxml");
        }
    }


    @FXML
    void onClickEditUser(ActionEvent event) throws IOException {
        User selectedUser = tblViewUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateUserView.fxml"));
            Parent root = loader.load();

            CreateUserController createUserController = loader.getController();
            createUserController.setUserForEditing(selectedUser); // Assuming this method exists and does what's required

            // If you're updating the view within the same stage
            mainViewController.setCenterView(root);


        } else {
            btnUpdateUser.setDisable(true);
        }
    }

    private void changeScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Node view = loader.load();
        mainViewController.setCenterView(view);
    }



}
