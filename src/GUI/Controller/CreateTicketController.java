package GUI.Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTicketController implements Initializable {
/*
    public MFXLegacyComboBox<String> ticketTypeBox;
    private Ticket_typeModel ticket_typeModel;
    public TableView ticketTable;
    public TableColumn<TicketType, String> ticketTypeTable;
    public TableColumn<Ticket, Integer> ticketPriceTable;
    private TicketModel ticketModel;
    public TextArea ticketDescription;
    @FXML
    private Button ticketLayout;

    @FXML
    private TextField ticketPrice;


    private TicketType selectedTicket_type;


    private TicketTypeManager ticketTypeManager;

*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

 /*

    public CreateTicketController() throws SQLException, IOException {
        ticketModel = new TicketModel();
        ticket_typeModel = new Ticket_typeModel();
        ticketTypeManager = new TicketTypeManager();

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
        List<TicketType> allTicketTypes = ticketTypeManager.getAllTicketType();

        ticketTypeBox.getItems().clear();
        for (TicketType ticketTypes : allTicketTypes) {
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


 */

}