package DAL.DB;

import BE.Event;
import BE.Reservations;
import DAL.IReservations;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO_DB implements IReservations {
    private MyDatabaseConnector databaseConnector;

    public ReservationDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }
    @Override
    public List<Reservations> getAllReservations() throws IOException {
        ArrayList<Reservations> allReservations = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.Reservations";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Map DB row to Reservations object
                String email = rs.getString("email");
                int id = rs.getInt("id");

                // Create an Event object and add it to the list
                Reservations reservations = new Reservations(id, email);
                allReservations.add(reservations);
            }

            return allReservations;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IOException("Could not get reservations from database", ex);
        }
    }

    public Reservations createReservation(Reservations reservations) {
        String sql = "INSERT INTO dbo.Reservations (email) VALUES (?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Bind parameters
            stmt.setString(1, reservations.getEmail());

            // Run the specified SQL statement
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating reservation failed, no rows affected.");
            }

            // Get the generated ID from the DB
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservations.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }

            return reservations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reservations deleteReservation(Reservations reservations) throws IOException {
        // SQL statement to delete an event from the events table
        String sql = "DELETE FROM dbo.Reservations WHERE id = (?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameter
            stmt.setInt(1, reservations.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException("Could not delete reservation in database", e);
        }

        return reservations;

    }
}

