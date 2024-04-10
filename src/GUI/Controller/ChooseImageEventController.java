package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ChooseImageEventController {

    private String selectedImagePath;
    @FXML
    private TextField fieldFilePath;

    private CreateEventController createEventController;

    public ChooseImageEventController() throws Exception {
        createEventController = new CreateEventController();
    }

    @FXML
    private void handleChooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Png, jpeg", "*.png", "*.jpeg"));

        File initialDirectory = new File("Resources/EventImages");
        fileChooser.setInitialDirectory(initialDirectory);

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {

            selectedImagePath = selectedFile.toURI().toString();
            fieldFilePath.setText(selectedImagePath);
            createEventController.setImagePath(selectedImagePath);
        }
    }



    @FXML
    private void handleSaveFile(ActionEvent actionEvent) throws Exception {

            Stage stage = (Stage) fieldFilePath.getScene().getWindow();
            stage.close();

    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldFilePath.getScene().getWindow();
        stage.close();
    }
}
