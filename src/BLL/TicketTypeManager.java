package BLL;

import BE.TicketType;
import DAL.DB.TicketTypeDAO_DB;
import DAL.ITicketType;

import java.sql.SQLException;
import java.util.List;

public class TicketTypeManager {
    private ITicketType ticketTypeDAO;

    public TicketTypeManager() throws SQLException {
        ticketTypeDAO = new TicketTypeDAO_DB();
    }

    public List<TicketType> getAllTicketType() throws SQLException {
        return ticketTypeDAO.getAllTicketType();
    }

    public TicketType createTicketType (TicketType TicketType) throws SQLException {
        return ticketTypeDAO.createTicketType(TicketType);
    }

    public TicketType getTicketTypeIdFromTitle(String ticketTypeTitle) throws SQLException{
        return ticketTypeDAO.getTicketTypeIdFromTitle(ticketTypeTitle);
    }

}
