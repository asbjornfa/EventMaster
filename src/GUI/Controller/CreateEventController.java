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
        setEventInformation(event);
    }
    private void setEventInformation(Event event) {
        if(event != null){
            eventTitleField.setText(event.getTitle());
            eventLocationField.setText(event.getLocation());
            eventDescriptionField.setText(event.getDescription());

            btnCreate.setText("Save");

        }
    }


    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {

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

        if (eventToEdit != null) {

            eventToEdit.setTitle(title);
            eventToEdit.setLocation(location);
            eventToEdit.setDescription(description);
            eventToEdit.setStartDate(startDate.atStartOfDay());
            eventToEdit.setEndDate(endDate.atStartOfDay());
            eventToEdit.setStartTime(startTime);
            eventToEdit.setEndTime(endTime);


            eventModel.updateEvent(eventToEdit);
        } else {

            eventModel.createEvent(title, location, startDate.atStartOfDay(), endDate.atStartOfDay(),
                    startTime, endTime, description);

        }


    }



}
