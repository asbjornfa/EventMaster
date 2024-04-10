package BLL;

import BE.Ticket_type;
import DAL.DB.TicketTypeDAO_DB;
import DAL.ITicket_type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class Ticket_typeManager {
    private ITicket_type ticket_typeDAO;

    public Ticket_typeManager() throws SQLException {
        ticket_typeDAO = new TicketTypeDAO_DB();

    }

    public List<Ticket_type> getAllTicketType() throws SQLException {
        return ticket_typeDAO.getAllTicketType();

    }

    public Ticket_type createTicketType (Ticket_type Ticket_type) throws SQLException {
        return ticket_typeDAO.createTicketType(Ticket_type);
    }

}
