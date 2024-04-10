package GUI.Model;

import BE.Ticket;
import BE.Ticket_type;
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

    public void createTicket(int price, String ticket_description, int ticket_typeId) throws IOException {
        Ticket ticket = new Ticket(price,ticket_description, ticket_typeId);

        ticketManager.createTicket(ticket);
        ticketToBeViewed.add(ticket);
    }

    public void deleteTicket(Ticket ticket) throws IOException {
        ticketManager.deleteTicket(ticket);
        ticketToBeViewed.remove(ticket);
    }


}
