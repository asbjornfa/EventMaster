package DAL.DB;

import BE.Event;
import DAL.IEventDataAccess;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
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

            String sql = "SELECT e.*, STRING_AGG(u.username, ', ') AS coordinators\n" +
                    "FROM Event e\n" +
                    "left JOIN AssignedEventCoordinators ec ON e.id = ec.eventId\n" +
                    "left JOIN users u ON ec.userId = u.id\n" +
                    "GROUP BY e.id,e.createdBy,e.description,e.endDate,e.endTime,e.location,e.startDate,e.startTime,e.title, e.imagePath;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Map DB row to Event object
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String location = rs.getString("location");
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalDate endDate = rs.getDate("endDate").toLocalDate();
                Time startTime = rs.getTime("startTime");
                Time endTime = rs.getTime("endTime");
                String description = rs.getString("description");
                String createdBy = rs.getString("createdBy");
                String imagePath = rs.getString("imagePath");
                String coordinators = rs.getString("coordinators");


                // Create an Event object and add it to the list
                Event event = new Event(id, title, location, startDate, endDate, startTime, endTime, description, createdBy, imagePath,coordinators);
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
        String sql = "INSERT INTO dbo.Event (title, location, startDate, endDate, startTime, endTime, description, imagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setString(1, event.getTitle());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, Date.valueOf(event.getStartDate()));
            stmt.setDate(4, Date.valueOf(event.getEndDate()));
            stmt.setTime(5, event.getStartTime());
            stmt.setTime(6, event.getEndTime());
            stmt.setString(7, event.getDescription());
            stmt.setString(8, event.getImagePath());

            // Execute the SQL statement to insert the new event
            stmt.executeUpdate();

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
            stmt.setDate(2, Date.valueOf(event.getStartDate()));
            stmt.setDate(3, Date.valueOf(event.getEndDate()));
            stmt.setTime(4, event.getStartTime());
            stmt.setTime(5, event.getStartTime());
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
