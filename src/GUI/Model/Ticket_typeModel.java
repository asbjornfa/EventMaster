package GUI.Model;

import BE.Ticket_type;
import BLL.Ticket_typeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Ticket_typeModel {
    private ObservableList<Ticket_type> viewTicketType;
    private Ticket_typeManager ticketTypeManager;

    public Ticket_typeModel() throws SQLException {
        ticketTypeManager = new Ticket_typeManager();

        viewTicketType = FXCollections.observableArrayList();
        viewTicketType.addAll(ticketTypeManager.getAllTicketType());

    }

    public ObservableList<Ticket_type> getObservableTicketType(){

        return viewTicketType;
    }

    public int createTicketType(String title) throws SQLException {
        Ticket_type ticket_type = new Ticket_type(title);

        ticketTypeManager.createTicketType(ticket_type);
        viewTicketType.add(ticket_type);


        return 0;
    }

    public int getTicketTypeIdByTitle(String title){
        for (Ticket_type ticketType : viewTicketType){
            if (ticketType.getTitle().equals(title)){
                return ticketType.getId();
            }
        }
        return -1; //if the title not found;
    }
}
