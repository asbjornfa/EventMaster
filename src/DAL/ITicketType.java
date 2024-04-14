package DAL;

import BE.TicketType;

import java.sql.SQLException;
import java.util.List;

public interface ITicketType {
    public List<TicketType> getAllTicketType() throws SQLException;
    public TicketType createTicketType(TicketType ticket_type) throws SQLException;
    public TicketType deleteTicketType(TicketType ticket_type);
    public TicketType getTicketTypeIdFromTitle(String ticketTypeTitle) throws SQLException;

}


