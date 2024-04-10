package DAL.DB;

import BE.Ticket_type;
import DAL.ITicket_type;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO_DB implements ITicket_type {
    private MyDatabaseConnector databaseConnector;


    public TicketTypeDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }
    @Override
    public List<Ticket_type> getAllTicketType() throws SQLException {
        ArrayList<Ticket_type> allTicketTypes = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String  sql = "SELECT * FROM dbo.Ticket_type";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                    int id = rs.getInt("id");
                    String title = rs.getString("Title");

                    Ticket_type ticket_type = new Ticket_type(id, title);
                    allTicketTypes.add(ticket_type);
            }
            return allTicketTypes;
        }
    }

    @Override
    public Ticket_type createTicketType(Ticket_type ticket_type) throws SQLException {
            String sql = "INSERT INTO dbo.Ticket_type (Title) VALUES (?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
             {


            stmt.setString(1, ticket_type.getTitle());

            stmt.executeUpdate();



            }
            return ticket_type;
        }



    @Override
    public Ticket_type deleteTicketType(Ticket_type ticket_type) {
        return null;
    }
}
