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
import javafx.scene.Node;
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

public class ActiveEventController implements Initializable, MainViewControllerAware {
    public TableView<Event> tblViewEvents;
    public TableColumn<Event, String> colTitle;
    public TableColumn<Event, LocalDate> colDate;
    //public TableColumn colApprovalDate;
    public TableColumn colAssigned;
    public TableColumn colLocation;

    public Button btnCreateEvent;
    public Button btnEventInformation;
    public Button btnEditEvent;
    public Button btnDeleteEvent;
    @FXML
    private Button btnCreateTickets;
    @FXML
    private Button btnAssign;

    private EventModel eventModel;

    private AssignCoordinatorModel assignCoordinatorModel;
    private MainViewController mainViewController;

    public ActiveEventController() throws Exception {
        eventModel = new EventModel();
        assignCoordinatorModel = new AssignCoordinatorModel();

    }

    // Method to set MainViewController
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
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
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        tblViewEvents.setItems(eventModel.getObservableEvents());

        // Disable the button by default
        btnAssign.setDisable(true);
        btnCreateTickets.setDisable(true);
        btnEditEvent.setDisable(true);
        btnEventInformation.setDisable(true);
        btnDeleteEvent.setDisable(true);

        // Add a listener to the TableView's selection model
        tblViewEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Enable the button if a row is selected, otherwise disable
            btnAssign.setDisable(newSelection == null);
            btnCreateTickets.setDisable(newSelection == null);
            btnEditEvent.setDisable(newSelection == null);
            btnEventInformation.setDisable(newSelection == null);
            btnDeleteEvent.setDisable(newSelection == null);
            btnCreateEvent.setDisable(newSelection != null);

        });
    }

    public void onClickEditEvent(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblViewEvents.getSelectionModel().getSelectedItem();
        if (selectedEvent !=null){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateEventView.fxml"));
        Parent root = loader.load();
        CreateEventController createEventController = loader.getController();

        // Pass the EventModel to the CreateEventController
        createEventController.setEventModel(eventModel);
        createEventController.setEventToEdit(selectedEvent);
        createEventController.setMainViewController(mainViewController);

        // Set the scene or update the main view
        mainViewController.setCenterView(root); // Adjust this line if you use different method to change views
            mainViewController.lblMenuTitle.setText("Edit event");
    }
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

    public void onClickAssign(ActionEvent actionEvent) throws IOException {

        Event selectedEvent = tblViewEvents.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AssignCoordinators.fxml"));
            Parent root = loader.load();
            AssignCoordinatorsController assignController = loader.getController();
            assignController.setSelectedEvent(selectedEvent);
            assignController.setActiveEventController(this);
            assignController.setMainViewController(mainViewController); // Pass MainViewController instance

            // If you're updating the view within the same stage
            mainViewController.setCenterView(root);
            mainViewController.lblMenuTitle.setText("Assign coordinators");
        }

    }

        public void onClickCreateTickets(ActionEvent event) throws IOException {

            Event selectedEvent = tblViewEvents.getSelectionModel().getSelectedItem();

            if (selectedEvent != null) {
                // Load the FXML file for CreateTicketView
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateTicketView.fxml"));
                Parent root = loader.load();

                // Access the controller
                CreateTicketViewController createTicketController = loader.getController();

                // Pass the eventId to the CreateTicketViewController
                createTicketController.setEventTitle(tblViewEvents.getSelectionModel().getSelectedItem().getTitle());

                // Pass the eventId to the CreateTicketViewController
                createTicketController.setEventId(selectedEvent.getId()); // Pass the eventId here

                createTicketController.setMainViewController(mainViewController); // Pass MainViewController instance

                // If you're updating the view within the same stage
                mainViewController.setCenterView(root);
                mainViewController.lblMenuTitle.setText("Create event tickets");
            }
        }

    public void onClickCreateEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateEventView.fxml"));
        Parent root = loader.load();
        CreateEventController createEventController = loader.getController();

        // Pass the EventModel to the CreateEventController
        createEventController.setEventModel(eventModel);

        createEventController.setMainViewController(mainViewController);
        // Set the scene or update the main view
        mainViewController.setCenterView(root); // Adjust this line if you use different method to change views
        mainViewController.lblMenuTitle.setText("Create event");
    }

    public void onClickEventInformation(ActionEvent event) {
        //A new view with all information about event..
    }


}

