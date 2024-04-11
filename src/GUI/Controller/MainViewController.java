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
import javafx.scene.control.MenuItem;
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
    @FXML
    private MenuItem menuTicketInformation;

    @FXML
    private MenuItem menuBuyTickets;

    @FXML
    private MenuItem menuActiveEvents;

    @FXML
    private MenuItem menuAuthorisedLogin;

    @FXML
    private MenuItem menuCreateEvent;

    @FXML
    private MenuItem menuCreateTicket;

    @FXML
    private MenuItem menuCreateUser;

    @FXML
    private MenuItem menuHome;

    @FXML
    private MenuItem menuLogOut;

    @FXML
    private MenuItem menuPendingEvents;

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
        updateMenuForInitialState();


        //loadActiveEvents();

    }
    /*public void loadActiveEvents() {
        try {

            List<Event> activeEvents = eventModel.getObservableEvents();

            for (int i = 0; i < activeEvents.size(); i++) {
                Event event = activeEvents.get(i);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EventSample.fxml"));
                Node eventSampleView = loader.load();

                EventSampleController eventSampleController = loader.getController();
                eventSampleController.setData(event);

                GridPane.setMargin(eventSampleView, new Insets(15));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/



    private void updateMenu(String role) {

        // Show additional menu items based on user role
        if ("admin".equalsIgnoreCase(role)) {
            // Admin-specific menu items
            menuUsers.setVisible(true);
            menuCreateUser.setVisible(true);
            menuHome.setVisible(true);
            menuActiveEvents.setVisible(true);
            menuPendingEvents.setVisible(true);
            menuLogOut.setVisible(true);

            menuAuthorisedLogin.setVisible(false);
            menuCreateTicket.setVisible(false);
            menuCreateEvent.setVisible(false);
            menuReservations.setVisible(false);
            menuBuyTickets.setVisible(false);
            menuTicketInformation.setVisible(false);

        } else if ("event coordinator".equalsIgnoreCase(role)) {
            // Event Coordinator-specific menu items
            menuHome.setVisible(true);
            menuActiveEvents.setVisible(true);
            menuCreateEvent.setVisible(true);
            menuPendingEvents.setVisible(true);
            menuReservations.setVisible(true);
            menuLogOut.setVisible(true);
            menuCreateTicket.setVisible(true);
            menuBuyTickets.setVisible(true);
            menuTicketInformation.setVisible(true);

            menuAuthorisedLogin.setVisible(false);
            menuUsers.setVisible(false);
            menuCreateUser.setVisible(false);
        }
    }

    private void updateMenuForInitialState() {
        menuHome.setVisible(true);
        menuAuthorisedLogin.setVisible(true);

        menuActiveEvents.setVisible(false);
        menuCreateEvent.setVisible(false);
        menuPendingEvents.setVisible(false);
        menuReservations.setVisible(false);
        menuLogOut.setVisible(false);
        menuCreateTicket.setVisible(false);
        menuUsers.setVisible(false);
        menuCreateUser.setVisible(false);
        menuBuyTickets.setVisible(false);
        menuTicketInformation.setVisible(false);
    }

    public void handleAuthorisedLogin(ActionEvent actionEvent) throws Exception {

        try {

            // Close the current scene
            Stage currentStage = (Stage) mainBorderPane.getScene().getWindow();
            currentStage.close();

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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load loginView.fxml");
            alert.showAndWait();
        }

    }

    public void handleHome(ActionEvent actionEvent) {
        mainBorderPane.setCenter(OriginalCenter);
    }

    public void createEventHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateEventView.fxml"));
        AnchorPane testView = loader.load();
        CreateEventController createEventView = loader.getController();
        createEventView.setEventModel(eventModel);
        mainBorderPane.setCenter(testView);
    }

    public void createUserHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane testView = FXMLLoader.load((getClass().getResource("/View/CreateUserView.fxml")));
        mainBorderPane.setCenter(testView);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pendingEventsHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PendingEvents.fxml"));
        Parent pendingEventsView = loader.load();

        PendingEventsController pendingEventsController = loader.getController();
        pendingEventsController.setMainViewController(this);

        mainBorderPane.setCenter(pendingEventsView);
    }

    public void setCenterView(Node node) {
        mainBorderPane.setCenter(node);
    }

    public void eventsHandle(ActionEvent event) {
    }

    public void createTicketHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane createTicket = FXMLLoader.load(getClass().getResource("/View/CreateTicket.fxml"));
        mainBorderPane.setCenter(createTicket);
    }

    public void activeEventsHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane activeEvents = FXMLLoader.load((getClass().getResource("/View/ActiveEvent.fxml")));
        mainBorderPane.setCenter(activeEvents);
    }

    public void reservationsHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane reservations = FXMLLoader.load(getClass().getResource("/View/ReservationsView.fxml"));
        mainBorderPane.setCenter(reservations);
    }

    public void logoutHandle(ActionEvent actionEvent) {
    }


    // Method to reopen the homepage
    public void reopenHomepage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
            Parent root = loader.load();

            // Get the stage from the main view
            Stage stage = (Stage) mainBorderPane.getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading homepage
        }
    }

    public void buyTicketHandle(ActionEvent actionEvent) throws IOException {
        AnchorPane buyTickets = FXMLLoader.load(getClass().getResource("/View/BuyTicket.fxml"));
        mainBorderPane.setCenter(buyTickets);
    }

    public void handleTicketInformation(ActionEvent actionEvent) throws IOException {
        AnchorPane ticketInformation = FXMLLoader.load(getClass().getResource("/View/TicketInformationView.fxml"));
        mainBorderPane.setCenter(ticketInformation);
    }
}