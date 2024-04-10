package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AssignCoordinatorModel;
import GUI.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class AssignCoordinatorsController implements Initializable {

    private UserModel userModel;
    private Stage stage; // Reference to this stage
    private Event selectedEvent;

    private ActiveEventController activeEventController;
    private AssignCoordinatorModel assignCoordinatorModel;

    @FXML
    private TableView<User> tblViewEventCoordinators;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colUsername;


    public AssignCoordinatorsController() throws SQLException {
        userModel = new UserModel();
        assignCoordinatorModel = new AssignCoordinatorModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

        tblViewEventCoordinators.setItems(userModel.getObservableUsers());
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public void setActiveEventController(ActiveEventController activeEventController) {
        this.activeEventController = activeEventController;
    }


    // Set the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onClickBtnCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    public void onClickBtnSave(ActionEvent event) {
        User selectedUser = tblViewEventCoordinators.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            assignCoordinatorModel.assignCoordinators(selectedEvent, selectedUser);
            activeEventController.updateTableView(); // Update the tableview in the ActiveEventController
            stage.close();
        }


    }
}
