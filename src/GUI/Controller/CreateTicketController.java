package GUI.Controller;
import BE.Ticket;
import GUI.Model.TicketModel;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CreateTicketController {
    public TicketViewController ticketViewController;
    public TableView ticketTable;
    public TableColumn ticketTypeTable;
    public TableColumn<Ticket, Integer> ticketPriceTable;
    private TicketModel ticketModel;
    public TextArea ticketDescription;
    @FXML
    private Button ticketLayout;

    @FXML
    private TextField ticketPrice;

    @FXML
    private TextField ticketType;


    public CreateTicketController() throws SQLException, IOException {
        ticketModel = new TicketModel();
    }

    @FXML
    private void onClickCreateEvent(ActionEvent event) {


    }

    public void onClickAddTicket(ActionEvent actionEvent) throws IOException {

        String tType = ticketType.getText();
        Integer tPrice = Integer.valueOf(ticketPrice.getText());
        String tDescription = ticketDescription.getText();

        ticketModel.createTicket(tPrice, tType, tDescription);

        // Opdater tabellen med billetdata
        ObservableList<Ticket> tickets = ticketModel.getObservableTicket();

        ticketTypeTable.setCellValueFactory(new PropertyValueFactory<>("ticket_layout"));
        ticketPriceTable.setCellValueFactory(new PropertyValueFactory<>("price"));


        ticketTable.setItems(tickets); // Tilføj billetdata til TableView
        ticketTable.refresh();

        if (ticketTable != null) {
            ticketType.clear();
            ticketPrice.clear();
        }


    }


    public void onClickDeleteTicket(ActionEvent actionEvent) throws IOException {
        Ticket selectedTicket = (Ticket) ticketTable.getSelectionModel().getSelectedItem();

        if (selectedTicket != null) {
            ticketModel.deleteTicket(selectedTicket);
            ticketTable.refresh();
        }
    }


    public void onClickShowTicket(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TicketView.Fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ticket View");
            stage.show();

        } catch (IOException e) {
            throw new IOException(e);
        }

    }
}


