package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AssignCoordinatorModel;
import GUI.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class AssignCoordinatorsController implements Initializable {

    @FXML
    private TableView<User> tblViewEventCoordinators;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colUsername;

    private UserModel userModel;

    private ActiveEventController activeEventController;
    private AssignCoordinatorModel assignCoordinatorModel;
    private MainViewController mainViewController;

    private Stage stage; // Reference to this stage
    private Event selectedEvent;

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

    // Method to set MainViewController
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
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
    public void onClickBtnCancel(ActionEvent event) throws IOException {
        mainViewController.setCenterView("/View/ActiveEvent.fxml");
        mainViewController.lblMenuTitle.setText("Events");
    }

    @FXML
    public void onClickBtnSave(ActionEvent event) throws IOException {
        User selectedUser = tblViewEventCoordinators.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            assignCoordinatorModel.assignCoordinators(selectedEvent, selectedUser);
            mainViewController.setCenterView("/View/ActiveEvent.fxml");
            mainViewController.lblMenuTitle.setText("Events");
        }
    }
}
