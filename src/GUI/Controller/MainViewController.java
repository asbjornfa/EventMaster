package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.EventModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public ImageView imageViewer;
    public Label lblUsername;
    public Label lblPosition;
    public Label lblMenuTitle;

    @FXML
    private MenuItem menuTickets;


    @FXML
    private MenuItem menuActiveEvents;


    @FXML
    private MenuItem menuCreateEvent;

    @FXML
    private MenuItem menuCreateTicket;

    @FXML
    private MenuItem menuCreateUser;


    @FXML
    private MenuItem menuLogOut;

    @FXML
    private MenuItem menuReservations;

    @FXML
    private MenuItem menuUsers;

    public BorderPane mainBorderPane;
    private Node OriginalCenter;
    private EventModel eventModel;
    private UserModel userModel;

    private LoginViewController loginViewController;


    public void setEventModel(EventModel eventModel) {

        this.eventModel = eventModel;
    }

    public MainViewController() throws Exception {
        userModel = new UserModel();
        eventModel = new EventModel();
    }

    public void setUser(User authenticatedUser) {
        // Check if the authenticated user is not null
        if (authenticatedUser != null) {
            String username = authenticatedUser.getUsername();
            String role = userModel.getPositionFromUser(username);
            // Update the menu based on the user's role
            lblUsername.setText(username);
            lblPosition.setText(role);
            updateMenu(role);
        } else {
            // Handle case when authenticatedUser is null
        }
    }

    // Method to set the LoginViewController
    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OriginalCenter = mainBorderPane.getCenter();
        lblMenuTitle.setText("");
        //loadActiveEvents();
    }

    private void updateMenu(String role) {

        // Show additional menu items based on user role
        if ("admin".equalsIgnoreCase(role)) {
            // Admin-specific menu items
            menuUsers.setVisible(true);
            menuCreateUser.setVisible(true);
            menuActiveEvents.setVisible(true);
            menuLogOut.setVisible(true);


            menuCreateTicket.setVisible(false);
            menuCreateEvent.setVisible(false);
            menuReservations.setVisible(true);
            menuTickets.setVisible(true);


        } else if ("event coordinator".equalsIgnoreCase(role)) {
            // Event Coordinator-specific menu items
            menuActiveEvents.setVisible(true);
            menuCreateEvent.setVisible(true);
            menuReservations.setVisible(false);
            menuCreateTicket.setVisible(false);
            menuLogOut.setVisible(true);
            menuTickets.setVisible(true);

            menuUsers.setVisible(false);
            menuCreateUser.setVisible(false);
        }
    }


    public void createEventHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateEventView.fxml"));
        AnchorPane testView = loader.load();
        CreateEventController createEventView = loader.getController();
        createEventView.setEventModel(eventModel);
        mainBorderPane.setCenter(testView);
        lblMenuTitle.setText("Create event");
    }

    public void createUserHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane testView = FXMLLoader.load((getClass().getResource("/View/CreateUserView.fxml")));
        mainBorderPane.setCenter(testView);
        lblMenuTitle.setText("Create user");
    }

    public void usersHandle(ActionEvent actionEvent) {
        try {
            // Load UsersView
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UsersView.fxml"));
            Parent usersView = loader.load();

            // Get the UsersViewController and set MainViewController
            UsersViewController usersViewController = loader.getController();
            usersViewController.setMainViewController(this);

            // Assume mainBorderPane is the container in MainViewController where you want to set the users view
            this.mainBorderPane.setCenter(usersView);
            lblMenuTitle.setText("Users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setCenterView(Node node) {
        mainBorderPane.setCenter(node);
    }

    public void createTicketHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane createTicket = FXMLLoader.load(getClass().getResource("/View/CreateTicketView.fxml"));
        mainBorderPane.setCenter(createTicket);
    }

    public void activeEventsHandle(ActionEvent actionEvent) throws IOException {
        // Load UsersView
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ActiveEvent.fxml"));
        Parent activeEvents = loader.load();

        // Get the UsersViewController and set MainViewController
        ActiveEventController activeEventController = loader.getController();
        activeEventController.setMainViewController(this);
        mainBorderPane.setCenter(activeEvents);
    }

    public void reservationsHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane reservations = FXMLLoader.load(getClass().getResource("/View/ReservationsView.fxml"));
        mainBorderPane.setCenter(reservations);
    }

    public void logoutHandle(ActionEvent actionEvent) {
        try {
            // Close the current stage
            Stage currentStage = (Stage) mainBorderPane.getScene().getWindow();
            currentStage.close();

            // Reopen the login page
            reopenLogin();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error while logging out
        }
    }


    // Method to reopen the login page
    private void reopenLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/loginView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            // Pass the stage to the LoginViewController
            LoginViewController loginViewController = loader.getController();
            loginViewController.setStage(stage);
            loginViewController.setMainController(this); // Set the MainViewController instance
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading login page
        }
    }


    public void ticketsHandle(ActionEvent event) throws IOException {
        // Load UsersView
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TicketViewTable.fxml"));
        Parent tickets = loader.load();

        TicketViewTableController ticketViewTableController = loader.getController();
        ticketViewTableController.setMainViewController(this);

        mainBorderPane.setCenter(tickets);
    }


    // Method in MainViewController to change the central view of the application
    public void setCenterView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();
            mainBorderPane.setCenter(newView); // Assuming borderPane is the container in MainViewController

            // Set the mainViewController reference in the newly loaded controller if it requires it
            Object controller = loader.getController();
            if (controller instanceof MainViewControllerAware) {
                ((MainViewControllerAware) controller).setMainViewController(this);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load the view");
            alert.showAndWait();
        }
    }
}