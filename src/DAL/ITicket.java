package DAL;

import BE.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ITicket {
    public List<Ticket> getAllTickets() throws IOException;
    public Ticket createTicket(Ticket ticket) throws IOException;
    public Ticket deleteTicket(Ticket ticket) throws IOException;
    public Ticket updateQuantityAvailable(Ticket ticket) throws SQLException;
    public Ticket updateTicket(Ticket ticket) throws SQLException;
}

