package GUI.Model;

import BE.TicketType;
import BLL.TicketTypeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class TicketTypeModel {

    private ObservableList<TicketType> viewTicketType;
    private TicketTypeManager ticketTypeManager;

    public TicketTypeModel() throws SQLException {
        ticketTypeManager = new TicketTypeManager();

        viewTicketType = FXCollections.observableArrayList();
        viewTicketType.addAll(ticketTypeManager.getAllTicketType());
    }

    public ObservableList<TicketType> getObservableTicketType() {
        return viewTicketType;
    }

    public void createTicketType(String title) throws SQLException {
        TicketType ticket_type = new TicketType(title);

        ticketTypeManager.createTicketType(ticket_type);
        viewTicketType.add(ticket_type);
    }

    public TicketType getTicketTypeIdFromTitle(String ticketTypeTitle) throws SQLException{
        return ticketTypeManager.getTicketTypeIdFromTitle(ticketTypeTitle);
    }
}
