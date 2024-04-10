package GUI.Model;

import BE.Event;
import BE.User;
import BLL.AssignCoordinatorManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class AssignCoordinatorModel {

    private AssignCoordinatorManager assignCoordinatorManager;


    public AssignCoordinatorModel() throws SQLException {
        assignCoordinatorManager = new AssignCoordinatorManager();
    }

    public void assignCoordinators(Event event, User user) {
        assignCoordinatorManager.assignCoordinators(event, user);
    }
}
