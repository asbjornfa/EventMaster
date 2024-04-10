package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AssignCoordinatorModel;
import GUI.Model.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ActiveEventController implements Initializable {
    public TableView<Event> tblViewEvents;
    public TableColumn<Event, String> colTitle;
    public TableColumn<Event, LocalDate> colDate;
    //public TableColumn colApprovalDate;
    public TableColumn colAssigned;

    @FXML
    private Button btnAssign;

    private EventModel eventModel;

    private AssignCoordinatorModel assignCoordinatorModel;

    public ActiveEventController() throws Exception {
        eventModel = new EventModel();
        assignCoordinatorModel = new AssignCoordinatorModel();

    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public void updateTableView() {
        tblViewEvents.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colAssigned.setCellValueFactory(new PropertyValueFactory<>("coordinators"));

        tblViewEvents.setItems(eventModel.getObservableEvents());

        // Disable the button by default
        btnAssign.setDisable(true);

        // Add a listener to the TableView's selection model
        tblViewEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Enable the button if a row is selected, otherwise disable
            btnAssign.setDisable(newSelection == null);
        });
    }

    public void onClickEditEvent(ActionEvent actionEvent) {
    }

    public void onClickDeleteEvent(ActionEvent actionEvent) {

        Event selectedEvent = tblViewEvents.getSelectionModel().getSelectedItem();


        if (selectedEvent != null) {
            try {
                eventModel.deleteEvent(selectedEvent);
                tblViewEvents.getItems().remove(selectedEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            System.out.println("Please select an event to delete.");
        }
    }

    public void onClickAssign(ActionEvent actionEvent) {

        Event selectedEvent = tblViewEvents.getSelectionModel().getSelectedItem();

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
            assignController.setActiveEventController(this);

            // Show the new stage
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load the AssignCoordinators view.");
        }

    }




}
