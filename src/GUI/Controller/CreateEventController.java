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
    private String selectedImagePath;



    public CreateEventController() {
    }

    /*public CreateEventController(EventModel eventModel) throws Exception {
        this.eventModel = eventModel;
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setImage();
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public void setSelectedImagePath(String selectedImagePath) {
        this.selectedImagePath = selectedImagePath;
    }
    public void setEventToEdit(Event event) {
        this.eventToEdit = event;
        // Populate UI fields with the data from eventToEdit
        setEventInformation(event);
    }

    public void setImage(String imagePath){
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toString());
        imageEvent.setImage(image);

        imageEvent.setPreserveRatio(false);
        imageEvent.setFitWidth(250);
        imageEvent.setFitHeight(200);
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
        LocalDate endDate = selectedDateTimeEnd.toLocalDate();

        // Extract time portion from LocalDateTime
        LocalTime startTime = selectedDateTimeStart.toLocalTime();
        LocalTime endTime = selectedDateTimeEnd.toLocalTime();

        // Convert LocalTime to Time
        Time startTimeSql = Time.valueOf(startTime);
        Time endTimeSql = Time.valueOf(endTime);


        if (eventToEdit != null) {

            eventToEdit.setTitle(title);
            eventToEdit.setLocation(location);
            eventToEdit.setDescription(description);
            eventToEdit.setStartDate(startDate);
            eventToEdit.setEndDate(endDate);
            eventToEdit.setStartTime(startTimeSql);
            eventToEdit.setEndTime(endTimeSql);


            eventModel.updateEvent(eventToEdit);
            showAlert("Event has been updated successfully!");
        } else {
            eventModel.createEvent(title, location, startDate, endDate,
                    startTimeSql, endTimeSql, description, selectedImagePath);
            showAlert("Event has been created successfully and can now be found in pending events!");
        }

        // Clear all fields after event creation
        clearFields();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        eventTitleField.clear();
        eventLocationField.clear();
        eventDescriptionField.clear();
    }

    @FXML
    private void handleImportImage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ChooseImageEventView.fxml"));
            Parent root = loader.load();
            ChooseImageEventController chooseImageEventController = loader.getController();
            chooseImageEventController.setCreateEventController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load fxml");
            alert.showAndWait();
        }

    }
}
