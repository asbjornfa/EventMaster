package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
import com.browniebytes.javafx.control.DateTimePicker;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateEventController {
    public DateTimePicker DTPickerEnd;
    @FXML
    private DateTimePicker DTPickerStart;
    @FXML
    private TextField eventLocationField;
    @FXML
    private TextArea eventDescriptionField;
    @FXML
    private TextField eventTitleField;
    private EventModel eventModel;
    private Event eventToEdit;
    @FXML
    private MFXButton btnCreate;



    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }
    public void setEventToEdit(Event event) {
        this.eventToEdit = event;
        // Populate UI fields with the data from eventToEdit
        updateUIForEditMode();
    }
    private void updateUIForEditMode() {
        boolean isEditMode = eventToEdit != null;
        // Set the visibility of the "Create" button based on whether it's in edit mode or not
        btnCreate.setVisible(!isEditMode);

        // Set text and color based on whether it's in edit mode or not
        if (isEditMode) {
            btnCreate.setText("Save"); // Change button text to "Save" in edit mode
            btnCreate.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Change button color
        } else {
            btnCreate.setText("Create"); // Change button text to "Create" in create mode
            btnCreate.setStyle("-fx-background-color: blue; -fx-text-fill: white;"); // Change button color
        }
    }


    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {
        if (eventToEdit != null) {
            // Handle editing logic
            // Populate UI fields with the data from eventToEdit
            eventTitleField.setText(eventToEdit.getTitle());
            eventLocationField.setText(eventToEdit.getLocation());
            eventDescriptionField.setText(eventToEdit.getDescription());

            // Set date and time pickers with the start and end dates/times of the event
            DTPickerStart.dateTimeProperty().set(eventToEdit.getStartDate().toLocalDate().atStartOfDay());
            DTPickerEnd.dateTimeProperty().set(LocalDateTime.from(eventToEdit.getEndDate().toLocalTime()));

            // You can access the eventToEdit fields and update them accordingly
        } else {
            // Handle creation logic
            String title = eventTitleField.getText();
            String location = eventLocationField.getText();
            String description = eventDescriptionField.getText();

            LocalDateTime selectedDateTimeStart = DTPickerStart.dateTimeProperty().get();
            LocalDateTime selectedDateTimeEnd = DTPickerEnd.dateTimeProperty().get();

            LocalDate startDate = selectedDateTimeStart.toLocalDate();
            LocalTime startTime = selectedDateTimeStart.toLocalTime();

            LocalDate endDate = selectedDateTimeEnd.toLocalDate();
            LocalTime endTime = selectedDateTimeEnd.toLocalTime();

            eventModel.createEvent(title, location, startDate.atStartOfDay(), endDate.atStartOfDay(),
                    startTime, endTime, description);

        }


    }




}
