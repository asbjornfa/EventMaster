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
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                int id = rs.getInt("id");

                // Create an Event object and add it to the list
                Reservations reservations = new Reservations(id, firstName, lastName, email);
                allReservations.add(reservations);
            }

            return allReservations;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IOException("Could not get reservations from database", ex);
        }
    }

    @Override
    public Reservations createReservation(Reservations reservations) throws IOException {
        // SQL statement to insert a new event into the events table
        String sql = "INSERT INTO dbo.Reservations (firstName, lastName, email) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setString(1, reservations.getFirstName());
            stmt.setString(2, reservations.getLastName());
            stmt.setString(3, reservations.getEmail());

            // Execute the SQL statement to insert the new event
            stmt.executeUpdate();

            return reservations;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException("Could not create reservation in database", e);
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

        System.out.println("DAO");
        return reservations;

    }
}

