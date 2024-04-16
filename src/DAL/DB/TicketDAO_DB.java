package DAL.DB;

import BE.Ticket;
import DAL.ITicket;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO_DB implements ITicket {

    private MyDatabaseConnector databaseConnector;

    public TicketDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public List<Ticket> getAllTickets() throws IOException {
        ArrayList<Ticket> allTickets = new ArrayList();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT t.id, t.price, t.ticketTypeId, t.eventId, t.quantityAvailable, e.title AS eventTitle, tt.title AS ticketTypeTitle\n" +
                    "FROM dbo.TicketsForEvent t\n" +
                    "JOIN dbo.Event e ON t.eventId = e.id\n" +
                    "JOIN dbo.TicketType tt ON t.ticketTypeId = tt.id";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Map DB row to Ticket object
                int id = rs.getInt("id");
                int price = rs.getInt("price");
                int ticketTypeId = rs.getInt("ticketTypeId");
                int eventId = rs.getInt("eventId");
                int quantityAvailable = rs.getInt("quantityAvailable");
                String eventTitle = rs.getString("eventTitle");
                String ticketTypeTitle = rs.getString("ticketTypeTitle");

                // Create an Ticket object and add it to the list
                Ticket ticket = new Ticket(id, price, ticketTypeId, eventId, quantityAvailable, eventTitle, ticketTypeTitle);
                allTickets.add(ticket);
            }

            // Return the list of events
            return allTickets;

        } catch (SQLException ex) {
            // Handle SQL exceptions
            ex.printStackTrace();
            // You might want to throw an IOException or a more specific exception related to database operations
            throw new IOException("Could not get ticket from database", ex);
        }
    }

    @Override
    public Ticket createTicket(Ticket ticket) throws IOException {
        // SQL statement to insert a new ticket into the tickets table
        String sql = "INSERT INTO dbo.TicketsForEvent (price, ticketTypeId, eventId, quantityAvailable) " +
                "VALUES (?, ?, ?,?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setInt(1, ticket.getPrice());
            stmt.setInt(2, ticket.getTicketTypeId());
            stmt.setInt(3, ticket.getEventId());
            stmt.setInt(4, ticket.getQuantityAvailable());

            stmt.executeUpdate();

            Ticket createdTicket = new Ticket(ticket.getPrice(), ticket.getTicketTypeId(), ticket.getEventId(), ticket.getQuantityAvailable());

            return createdTicket;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket deleteTicket(Ticket ticket) throws IOException {
        String sql = "DELETE FROM dbo.TicketsForEvent WHERE Id =(?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, ticket.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticket;
    }

    @Override
    public Ticket updateQuantityAvailable(Ticket ticket) throws SQLException {
        String sql = "UPDATE dbo.TicketsForEvent SET quantityAvailable = ? WHERE id = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setInt(1, ticket.getQuantityAvailable());
            stmt.setInt(2, ticket.getId());

            stmt.executeUpdate();


        }

        return ticket;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE dbo.TicketsForEvent SET price = ?, quantityAvailable = ?,  WHERE id = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setInt(1, ticket.getPrice());
            stmt.setInt(2, ticket.getQuantityAvailable());
            stmt.setInt(3, ticket.getId());

            stmt.executeUpdate();


        }

        return ticket;
    }

    }

