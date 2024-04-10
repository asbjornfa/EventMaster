package GUI.Controller;
import BE.Ticket;
import BE.Ticket_type;
import BLL.Ticket_typeManager;
import GUI.Model.TicketModel;

import GUI.Model.Ticket_typeModel;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreateTicketController implements Initializable {

    public MFXLegacyComboBox<String> ticketTypeBox;
    private Ticket_typeModel ticket_typeModel;
    public TableView ticketTable;
    public TableColumn<Ticket_type, String> ticketTypeTable;
    public TableColumn<Ticket, Integer> ticketPriceTable;
    private TicketModel ticketModel;
    public TextArea ticketDescription;
    @FXML
    private Button ticketLayout;

    @FXML
    private TextField ticketPrice;


    private Ticket_type selectedTicket_type;


    private Ticket_typeManager ticketTypeManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketTypeTable.setCellValueFactory(new PropertyValueFactory<>("title"));
        ticketPriceTable.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            ticketModel = new TicketModel();
            ticket_typeModel = new Ticket_typeModel();
            loadTicketTypes();
            ticketTable.setItems(ticketModel.getObservableTicket());




        } catch (SQLException | IOException e) {

        }
    }

    public CreateTicketController() throws SQLException, IOException {
        ticketModel = new TicketModel();
        ticket_typeModel = new Ticket_typeModel();
        ticketTypeManager = new Ticket_typeManager();

    }

    @FXML
    private void onClickCreateEvent(ActionEvent event) {


    }
    public void onClickAddTicket(ActionEvent actionEvent) throws IOException, SQLException {
        String tType = ticketTypeBox.getSelectionModel().getSelectedItem();


        if (tType != null) {
            int ticket_typeId = ticket_typeModel.getTicketTypeIdByTitle(tType);

            Integer tPrice = Integer.valueOf(ticketPrice.getText());
            String tDescription = ticketDescription.getText();

            if (!ticketTypeBox.isValid())
                ticketTypeBox.getId();
            ticketModel.createTicket(tPrice, tDescription, ticket_typeId);

        }
    }
    public void loadTicketTypes() throws SQLException {
        List<Ticket_type> allTicketTypes = ticketTypeManager.getAllTicketType();

        ticketTypeBox.getItems().clear();
        for (Ticket_type ticketTypes : allTicketTypes) {
            ticketTypeBox.getItems().add(ticketTypes.getTitle());
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