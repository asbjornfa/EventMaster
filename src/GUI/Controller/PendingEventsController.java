package GUI.Controller;

import BE.Event;
import BE.User;
import BLL.EventManager;
import GUI.Model.AssignCoordinatorModel;
import GUI.Model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PendingEventsController implements Initializable {

    private EventManager eventManager;
    private EventModel eventModel;
    private MainViewController mainViewController;
    private AssignCoordinatorModel assignCoordinatorModel;

    @FXML
    private TableView<Event> tblPendingEvents;
    @FXML
    private TableColumn<Event, String> colTitle;
    @FXML
    private TableColumn<Event, LocalDateTime> colDate;
    @FXML
    private TableColumn<Event, String> colCreatedBy;
    @FXML
    private TableColumn colAssignedTo;

    @FXML
    private Button btnEditEvent;

    @FXML
    private Button btnAssignCoordinator;

    public PendingEventsController() throws Exception {
    try{
        eventManager = new EventManager();
        eventModel = new EventModel();
        assignCoordinatorModel = new AssignCoordinatorModel();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("coordinators"));

        tblPendingEvents.setItems(eventModel.getObservableEvents());


        // Disable the button by default
        btnAssignCoordinator.setDisable(true);

        // Add a listener to the TableView's selection model
        tblPendingEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Enable the button if a row is selected, otherwise disable
            btnAssignCoordinator.setDisable(newSelection == null);
        });
    }

    public void updateTableView() {
        tblPendingEvents.refresh();
    }

    @FXML
    private void btnAssignCoordinator(ActionEvent actionEvent) {
        Event selectedEvent = tblPendingEvents.getSelectionModel().getSelectedItem();

        try {
            // Load the FXML file for the AssignCoordinators view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AssignCoordinators.fxml"));

            Parent root = loader.load();

            // Create a new stage (window)
            Stage stage = new Stage();
            stage.setTitle("Assign Coordinators");

            // Set the scene on the stage with the loaded parent node
            stage.setScene(new Scene(root));

            // Get the controller and set the stage
            AssignCoordinatorsController assignController = loader.getController();
            assignController.setStage(stage);
            assignController.setSelectedEvent(selectedEvent);
            assignController.setPendingEventController(this);

            // Show the new stage
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load the AssignCoordinators view.");
        }

    }


    @FXML
    private void btnApproveEvent(ActionEvent actionEvent) {
    }

    @FXML
    private void btnEditEvent(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblPendingEvents.getSelectionModel().getSelectedItem();

        if(selectedEvent != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/createEventView.fxml"));
            Parent root = loader.load();

            CreateEventController createEventController = loader.getController();
            createEventController.setEventToEdit(selectedEvent);

            mainViewController.setCenterView(root);

        } else {

            btnEditEvent.setDisable(true);
        }
    }


    @FXML
    private void btnDeleteEvent(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblPendingEvents.getSelectionModel().getSelectedItem();

        if(selectedEvent != null){
            eventModel.deleteEvent(selectedEvent);
            eventModel.getObservableEvents().remove(selectedEvent);

            clearSelection();
        }
    }


    public void clearSelection() {

        tblPendingEvents.getSelectionModel().clearSelection();
    }

    void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }
}
