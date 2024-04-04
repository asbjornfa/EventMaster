package DAL;

import BE.Ticket;

import java.io.IOException;
import java.util.List;

public interface ITicket {
    public List<Ticket> getAllTickets() throws IOException;
    public Ticket createTicket(Ticket ticket) throws IOException;
}
