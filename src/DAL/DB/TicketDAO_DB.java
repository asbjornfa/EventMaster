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

            String sql = "SELECT * FROM dbo.Event";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Map DB row to Ticket object
                int id = rs.getInt("id");
                int price = rs.getInt("price");
                String ticket_layout = rs.getString("ticket_layout");
                String ticket_description = rs.getString("ticket_description");




                // Create an Ticket object and add it to the list
                    Ticket ticket = new Ticket(id, price,ticket_layout,ticket_description);
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
        String sql = "INSERT INTO dbo.Event (price, ticket_layout, ticket_description) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters
            stmt.setInt(1, ticket.getPrice());
            stmt.setString(2, ticket.getTicket_layout());
            stmt.setString(3, ticket.getTicket_description());

            stmt.executeUpdate();

            Ticket createdTicket = new Ticket(ticket.getPrice(), ticket.getTicket_layout(), ticket.getTicket_description());

            return createdTicket;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
