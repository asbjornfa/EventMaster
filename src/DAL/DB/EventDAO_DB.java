package DAL.DB;

import BE.Event;
import DAL.IEventDataAccess;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO_DB implements IEventDataAccess {

    private MyDatabaseConnector databaseConnector;

    public EventDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public List<Event> getAllEvents() throws IOException {
        ArrayList<Event> allEvents = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.Event";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Map DB row to Event object
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String location = rs.getString("location");
                LocalDateTime startDate = rs.getTimestamp("startDate").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("endDate").toLocalDateTime();
                LocalTime startTime = rs.getTime("startTime").toLocalTime();
                LocalTime endTime = rs.getTime("endTime").toLocalTime();
                String description = rs.getString("description");
                String createdBy = rs.getString("createdBy");


                // Create an Event object and add it to the list
                Event event = new Event(id, title, location, startDate, endDate, startTime, endTime, description, createdBy);
                allEvents.add(event);
            }

            // Return the list of events
            return allEvents;

        } catch (SQLException ex) {
            // Handle SQL exceptions
            ex.printStackTrace();
            // You might want to throw an IOException or a more specific exception related to database operations
            throw new IOException("Could not get events from database", ex);
        }
    }

    @Override
    public Event createEvent(Event event) throws IOException {
        // SQL statement to insert a new event into the events table
        String sql = "INSERT INTO dbo.Event (title, location, startDate, endDate, startTime, endTime, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setString(1, event.getTitle());
            stmt.setString(2, event.getLocation());
            stmt.setTimestamp(3, Timestamp.valueOf(event.getStartDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(event.getEndDate()));
            stmt.setTime(5, Time.valueOf(event.getStartTime()));
            stmt.setTime(6, Time.valueOf(event.getEndTime()));
            stmt.setString(7, event.getDescription());

            // Execute the SQL statement to insert the new event
            stmt.executeUpdate();

            // Get the generated ID from the database (if applicable)
            /*ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Set the ID of the created event
            event.setId(id);

            // Return the created event

             */
            return event;

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            // You might want to throw an IOException or a more specific exception related to database operations
            throw new IOException("Could not create event in database", e);
        }
    }

    @Override
    public Event updateEvent(Event event) throws IOException {
// SQL statement to update an event in the events table
        String sql = "UPDATE dbo.Event SET title = ?, startDate = ?, endDate = ?, startTime = ?, endTime = ?, description = ?, createdBy = ? WHERE id = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setString(1, event.getTitle());
            stmt.setTimestamp(2, Timestamp.valueOf(event.getStartDate()));
            stmt.setTimestamp(3, Timestamp.valueOf(event.getEndDate()));
            stmt.setTime(4, Time.valueOf(event.getStartTime()));
            stmt.setTime(5, Time.valueOf(event.getStartTime()));
            stmt.setString(6, event.getDescription());
            stmt.setString(7, event.getCreatedBy());
            stmt.setInt(8, event.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            // You might want to throw an IOException or a more specific exception related to database operations
            throw new IOException("Could not update event in database", e);
        }

        // Return the updated event
        return event;
    }

    @Override
    public Event deleteEvent(Event event) throws IOException {
        // SQL statement to delete an event from the events table
        String sql = "DELETE FROM dbo.Event WHERE id = (?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameter
            stmt.setInt(1, event.getId());

            // Execute the SQL statement to delete the event
            int rowsAffected = stmt.executeUpdate();

            // Check if the event was deleted successfully
            if (rowsAffected == 0) {
                throw new IOException("Event with ID " + event.getId() + " not found");
            }

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            // You might want to throw an IOException or a more specific exception related to database operations
            throw new IOException("Could not delete event in database", e);
        }

        // Return the deleted event
        return event;
    }
}
