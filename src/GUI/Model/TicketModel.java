package GUI.Model;

import BE.Ticket;
import BE.User;
import BLL.TicketManager;
import BLL.UserManager;
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

    public ObservableList<Ticket> getObservableUsers() {

        return ticketToBeViewed;
    }

    public void createTicket(int price, String ticket_layout, String ticket_description) throws IOException {
        Ticket ticket = new Ticket(price, ticket_layout, ticket_description);

        ticketManager.createTicket(ticket);
        ticketToBeViewed.add(ticket);
    }
}
