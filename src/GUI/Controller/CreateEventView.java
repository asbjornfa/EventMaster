package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateEventView {
    private EventModel eventModel;

    @FXML
    private TextField eventTitleField;

    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {
        String title = eventTitleField.getText();

        //Event newEvent = new Event(title);


        eventModel.createEvent(eventTitleField.getText());

    }
}
