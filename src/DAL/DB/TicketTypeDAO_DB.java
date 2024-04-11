package DAL.DB;

import BE.TicketType;
import DAL.ITicketType;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO_DB implements ITicketType {
    private MyDatabaseConnector databaseConnector;


    public TicketTypeDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }
    @Override
    public List<TicketType> getAllTicketType() throws SQLException {
        ArrayList<TicketType> allTicketTypes = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String  sql = "SELECT * FROM dbo.TicketType";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                    int id = rs.getInt("id");
                    String title = rs.getString("title");

                    TicketType ticket_type = new TicketType(id, title);
                    allTicketTypes.add(ticket_type);
            }
            return allTicketTypes;
        }
    }

    @Override
    public TicketType createTicketType(TicketType ticketType) throws SQLException {
            String sql = "INSERT INTO dbo.TicketType (Title) VALUES (?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
             {


            stmt.setString(1, ticketType.getTitle());

            stmt.executeUpdate();



            }
            return ticketType;
        }



    @Override
    public TicketType deleteTicketType(TicketType ticketType) {
        return null;
    }
}
