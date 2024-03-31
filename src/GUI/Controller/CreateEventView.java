package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
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
    @FXML
    private TextField eventLocationField;
    @FXML
    private MFXDatePicker eventStartDateField;
    @FXML
    private MFXDatePicker eventEndDateField;
    @FXML
    private MenuButton eventStartTimeField;
    @FXML
    private MenuButton eventEndTimeField;
    @FXML
    private TextArea eventDescriptionField;
    @FXML
    private TextField eventTitleField;
    private EventModel eventModel;
    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {
       /* String title = eventTitleField.getText();
        String location = eventLocationField.getText();
        LocalDateTime startDate = eventStartDateField.getValue().atStartOfDay();
        LocalDateTime endDate = eventEndDateField.getValue().atStartOfDay();
        LocalTime startTime = eventStartTimeField.getValue;
        LocalTime endTime = eventEndTimeField.getItems();
        String description = eventDescriptionField.getText();



        Event newEvent = new Event(title, location, startDate)



        //Event newEvent = new Event(title);


        eventModel.createEvent(title, location, startDate);
*/
    }
}
