package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
import com.browniebytes.javafx.control.DateTimePicker;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateEventView {
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

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {
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
        System.out.println("Controller works");

    }


}
