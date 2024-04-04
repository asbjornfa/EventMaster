package GUI.Controller;

import BE.Event;
import BE.User;
import BLL.EventManager;
import GUI.Model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PendingEventsController implements Initializable {
    @FXML
    private TableView<Event> tblPendingEvents;
    @FXML
    private TableColumn<Event, String> colTitle;
    @FXML
    private TableColumn<Event, LocalDateTime> colDate;
    @FXML
    private TableColumn<Event, String> colCreatedBy;
    @FXML
    private TableColumn<Event, String> colAssignedTo;
    private EventManager eventManager;
    private EventModel eventModel;

    public PendingEventsController() throws Exception {
    try{
        eventManager = new EventManager();
        eventModel = new EventModel();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        tblPendingEvents.setItems(eventModel.getObservableEvents());
    }
    @FXML
    private void btnAssignCoordinator(ActionEvent actionEvent) {
    }

    @FXML
    private void btnApproveEvent(ActionEvent actionEvent) {
    }

    @FXML
    private void btnEditEvent(ActionEvent actionEvent) {
        Event selectedEvent = tblPendingEvents.getSelectionModel().getSelectedItem();

        if(selectedEvent != null) {
            openEditEventView(selectedEvent);

        }
    }

    @FXML
    private void openEditEventView(Event evenToEdit) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateEventView.fxml"));
            Parent root = loader.load();

            CreateEventController controller = loader.getController();
            controller.setEventModel(eventModel);
            controller.setEventToEdit(evenToEdit);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
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
}
