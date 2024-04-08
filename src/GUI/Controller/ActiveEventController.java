package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    //public TableColumn colAssigned;

    private EventModel eventModel;

    public ActiveEventController() throws Exception {
        eventModel = new EventModel();

    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        // Load events data into the table
        try {
            loadEventsData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEventsData() {
        ObservableList<Event> events = eventModel.getObservableEvents();
        tblViewEvents.setItems(events);
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
    }



}
