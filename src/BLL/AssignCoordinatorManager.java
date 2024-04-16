package BLL;

import BE.Event;
import BE.User;
import DAL.DB.AssignCoordinatorsDAO_DB;
import DAL.IAssignCoordinators;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignCoordinatorManager {

    private IAssignCoordinators assignCoordinatorsDAO;

    public AssignCoordinatorManager() throws SQLException {
        assignCoordinatorsDAO = new AssignCoordinatorsDAO_DB();
    }

    public void assignCoordinators(Event event, User user) {
        assignCoordinatorsDAO.assignCoordinators(event, user);
    }

}


