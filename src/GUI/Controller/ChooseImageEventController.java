package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ChooseImageEventController {

    @FXML
    private TextField fieldFilePath;
    private String selectedImagePath;
    private CreateEventController createEventController;

    public ChooseImageEventController() {

    }

    public void setCreateEventController(CreateEventController createEventController) {
        this.createEventController = createEventController;
    }
    @FXML
    private void handleChooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("vælg Billede");
        File file = fileChooser.showOpenDialog(fieldFilePath.getScene().getWindow());
        if(file != null) {
            fieldFilePath.setText(file.getAbsolutePath());
        }
    }



    @FXML
    private void handleSaveFile(ActionEvent actionEvent) throws Exception {
        selectedImagePath = fieldFilePath.getText();

        createEventController.setImage(selectedImagePath);

        saveImageFile(selectedImagePath);

        createEventController.setSelectedImagePath(selectedImagePath);
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldFilePath.getScene().getWindow();
        stage.close();
    }

    public String saveImageFile(String sourceImagePath) {
        try {
            // Definer stien til mappen, hvor du vil gemme billedet
            String targetDirectoryPath = "Resources/EventImages/";

            // Få filnavnet fra den oprindelige sti
            Path sourcePath = Paths.get(sourceImagePath);
            String fileName = sourcePath.getFileName().toString();

            // Opret stien til den nye filplacering
            Path targetPath = Paths.get(targetDirectoryPath + fileName);

            // Kopier filen til den nye placering
            // Hvis filen allerede eksisterer, vil den blive erstattet (REPLACE_EXISTING)
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Returner den nye sti uden det fulde filsystem sti
            return targetDirectoryPath + fileName;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
