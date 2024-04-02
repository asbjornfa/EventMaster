
import GUI.Controller.MainViewController;
import GUI.Model.EventModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        EventModel eventModel = new EventModel();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
        Parent root = loader.load();


        MainViewController controller = loader.getController();
        controller.setEventModel(eventModel);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
