package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
import com.browniebytes.javafx.control.DateTimePicker;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable{

    @FXML
    private DateTimePicker DTPickerEnd;
    @FXML
    private MFXButton btnCancel;
    @FXML
    private DateTimePicker DTPickerStart;
    @FXML
    private TextField eventLocationField;
    @FXML
    private TextArea eventDescriptionField;
    @FXML
    private TextField eventTitleField;
    @FXML
    private MFXButton btnCreate;

    private EventModel eventModel;

    private Event eventToEdit;

    private MainViewController mainViewController;

    public CreateEventController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    // Method to set MainViewController
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void setEventToEdit(Event event) {
        this.eventToEdit = event;
        // Populate UI fields with the data from eventToEdit
        setEventInformation(event);
    }

    private void setEventInformation(Event event) {
        if(event != null){
            eventTitleField.setText(event.getTitle());
            eventLocationField.setText(event.getLocation());
            eventDescriptionField.setText(event.getDescription());

            btnCreate.setText("Update event");
            btnCreate.setStyle("-fx-background-color: #FBBB2C");
        }
    }


    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {
        // Handle creation logic for events
        // Retrieve input data from text fields
        String title = eventTitleField.getText();
        String location = eventLocationField.getText();
        String description = eventDescriptionField.getText();

        // Retrieve selected start and end date-time values
        LocalDateTime selectedDateTimeStart = DTPickerStart.dateTimeProperty().get();
        LocalDateTime selectedDateTimeEnd = DTPickerEnd.dateTimeProperty().get();

        // Extract date portion from LocalDateTime
        LocalDate startDate = selectedDateTimeStart.toLocalDate();
        LocalDate endDate = selectedDateTimeEnd.toLocalDate();

        // Extract time portion from LocalDateTime and convert to Time objects
        LocalTime startTime = selectedDateTimeStart.toLocalTime();
        LocalTime endTime = selectedDateTimeEnd.toLocalTime();
        Time startTimeSql = Time.valueOf(startTime);
        Time endTimeSql = Time.valueOf(endTime);

        // Check if editing an existing event
        if (eventToEdit != null) {
            // Update existing event with new data
            eventToEdit.setTitle(title);
            eventToEdit.setLocation(location);
            eventToEdit.setDescription(description);
            eventToEdit.setStartDate(startDate);
            eventToEdit.setEndDate(endDate);
            eventToEdit.setStartTime(startTimeSql);
            eventToEdit.setEndTime(endTimeSql);

            // Update event in the model
            eventModel.updateEvent(eventToEdit);
        } else {
            // Create a new event with the provided data
            eventModel.createEvent(title, location, startDate, endDate,
                    startTimeSql, endTimeSql, description);
        }

        // Navigate back to the main event view
        mainViewController.setCenterView("/View/ActiveEvent.fxml");
        mainViewController.lblMenuTitle.setText("Events");
    }

    // Method to clear input fields
    private void clearFields() {
        eventTitleField.clear();
        eventLocationField.clear();
        eventDescriptionField.clear();
    }

    // Method to handle cancel button click event
    public void onClickCancel(ActionEvent event) throws IOException {
        // Navigate back to the main event view when cancel button is clicked
        mainViewController.setCenterView("/View/ActiveEvent.fxml");
        mainViewController.lblMenuTitle.setText("Events");
    }
}
