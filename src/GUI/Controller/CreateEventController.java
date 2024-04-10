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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {
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
    @FXML
    private ImageView imageEvent;
    private String imagePath;



    public CreateEventController() throws Exception {
        eventModel = new EventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImage();
    }

    public void setEventModel(EventModel eventModel) {

        this.eventModel = eventModel;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        setImage();
    }

    public void setEventToEdit(Event event) {
        this.eventToEdit = event;
        // Populate UI fields with the data from eventToEdit
        setEventInformation(event);
    }

    public void setImage() {
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image(imagePath);
            imageEvent.setImage(image);
        }
    }

    private void setEventInformation(Event event) {
        if(event != null){
            eventTitleField.setText(event.getTitle());
            eventLocationField.setText(event.getLocation());
            eventDescriptionField.setText(event.getDescription());

            btnCreate.setText("Save");
            btnCreate.setStyle("-fx-background-color: #FBBB2C");
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
                    startTime, endTime, description, imagePath);

        }


    }


    @FXML
    private void handleImportImage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ChooseImageEventView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load BuyTicket2.fxml");
            alert.showAndWait();
        }

    }


}
