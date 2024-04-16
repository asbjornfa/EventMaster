package GUI.Model;

import BE.Ticket;
import BLL.TicketManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class TicketModel {
    private TicketManager ticketManager;
    private ObservableList<Ticket> ticketToBeViewed;

    public TicketModel() throws SQLException, IOException {
        ticketManager = new TicketManager();

        ticketToBeViewed = FXCollections.observableArrayList();
        ticketToBeViewed.addAll(ticketManager.getAllTickets());
    }

    public ObservableList<Ticket> getObservableTicket() {

        return ticketToBeViewed;
    }

    public void createTicket(int price, int ticketTypeId, int eventId, int quantityAvailable) throws IOException {
        Ticket ticket = new Ticket(price,ticketTypeId,eventId,quantityAvailable);

        ticketManager.createTicket(ticket);
        ticketToBeViewed.add(ticket);
    }

    public void deleteTicket(Ticket ticket) throws IOException {
        ticketManager.deleteTicket(ticket);
        ticketToBeViewed.remove(ticket);
    }

    public void updateQuantityAvailable(Ticket ticket) throws IOException, SQLException {
        ticketManager.updateQuantityAvailable(ticket);
    }

    public void updateTicket(Ticket ticket) throws IOException, SQLException {
        ticketManager.updateTicket(ticket);
    }


}
