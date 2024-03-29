package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersViewController implements Initializable {

    private UserModel userModel;

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
    void onClickCreateUser(ActionEvent event) {

    }

    @FXML
    void onClickDeleteUser(ActionEvent event) {
        User selectedUser = tblViewUsers.getSelectionModel().getSelectedItem();

        if(selectedUser != null){
            userModel.deleteUser(selectedUser);
            userModel.getObservableUsers().remove(selectedUser);

            clearSelection();
        }
    }

    @FXML
    void onClickEditUser(ActionEvent event) {

    }


}
