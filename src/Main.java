
import GUI.Controller.ActiveEventController;
import GUI.Controller.LoginViewController;
import GUI.Controller.MainViewController;
import GUI.Model.EventModel;
import GUI.Model.UserModel;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/loginView.fxml"));
        Parent root = loader.load();

        LoginViewController loginController = loader.getController();
        loginController.setStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
