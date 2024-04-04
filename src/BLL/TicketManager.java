package BLL;

import BE.Ticket;
import BE.User;
import DAL.DB.EventDAO_DB;
import DAL.DB.TicketDAO_DB;
import DAL.ITicket;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketManager {
    private ITicket ticketDAO;

    public TicketManager() throws SQLException {
        ticketDAO = new TicketDAO_DB();
    }

    public List<Ticket> getAllTickets() throws IOException {
        return ticketDAO.getAllTickets();
    }

    public Ticket createTicket(Ticket newTicket) throws IOException {

        return ticketDAO.createTicket(newTicket);
    }

}
