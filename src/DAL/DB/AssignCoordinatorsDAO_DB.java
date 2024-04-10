package DAL.DB;

import BE.Event;
import BE.User;
import DAL.IAssignCoordinators;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignCoordinatorsDAO_DB implements IAssignCoordinators {

    private MyDatabaseConnector databaseConnector;

    public AssignCoordinatorsDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }


        @Override
    public void assignCoordinators(Event event, User user) {

            String sql =
                    "INSERT INTO AssignedEventCoordinators (eventId, userId) VALUES (?, ?)";

            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, event.getId());
                stmt.setInt(2, user.getId());

                stmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
