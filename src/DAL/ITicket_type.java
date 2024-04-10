package DAL;

import BE.Ticket_type;

import java.sql.SQLException;
import java.util.List;

public interface ITicket_type {
    public List<Ticket_type> getAllTicketType() throws SQLException;
    public Ticket_type createTicketType(Ticket_type ticket_type) throws SQLException;
    public Ticket_type deleteTicketType(Ticket_type ticket_type);

    }


