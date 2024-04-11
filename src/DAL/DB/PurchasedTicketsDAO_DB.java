package DAL.DB;

import BE.PurchasedTickets;
import BE.Reservations;
import DAL.IPurchasedTickets;
import DAL.IReservations;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchasedTicketsDAO_DB implements IPurchasedTickets {

        private MyDatabaseConnector databaseConnector;

        public PurchasedTicketsDAO_DB() throws SQLException {
            databaseConnector = new MyDatabaseConnector();
        }

    @Override
    public List<PurchasedTickets> getAllPurchasedTickets() throws IOException {
        ArrayList<PurchasedTickets> allPurchasedTickets = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.PurchasedTickets";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Map DB row to Reservations object
                int id = rs.getInt("id");
                int reservationId = rs.getInt("reservationId");
                int ticketTypeId = rs.getInt("ticketTypeId");
                int eventId = rs.getInt("eventId");
                String qrCode = rs.getString("qrCode");
                int quantity = rs.getInt("quantity");

                // Create an Event object and add it to the list
                PurchasedTickets purchasedTickets = new PurchasedTickets(id, reservationId,ticketTypeId,eventId,qrCode,quantity);
                allPurchasedTickets.add(purchasedTickets);
            }

            return allPurchasedTickets;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IOException(ex);
        }
    }

    @Override
    public PurchasedTickets createPurchasedTickets(PurchasedTickets purchasedTickets) throws IOException {
        String sql = "INSERT INTO dbo.PurchasedTickets (reservationId, ticketTypeId, eventId, qrCode, quantity) " +
                "VALUES (?,?,?,?,?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setInt(1, purchasedTickets.getReservationId());
            stmt.setInt(2, purchasedTickets.getTicketTypeId());
            stmt.setInt(3, purchasedTickets.getEventId());
            stmt.setString(4, purchasedTickets.getQrCode());
            stmt.setInt(5, purchasedTickets.getQuantity());

            // Execute the SQL statement to insert the new event
            stmt.executeUpdate();

            return purchasedTickets;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    @Override
    public void deletePurchasedTickets(PurchasedTickets purchasedTickets) throws IOException {
        String sql = "DELETE FROM dbo.PurchasedTickets WHERE id = (?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameter
            stmt.setInt(1, purchasedTickets.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

    }
}



