package GUI.Model;

import BE.PurchasedTickets;
import BE.User;
import BLL.PurchasedTicketsManager;
import BLL.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class PurchasedTicketsModel {

    private ObservableList<PurchasedTickets> purchasedTicketsToBeViewed;

    private PurchasedTicketsManager purchasedTicketsManager;

    public PurchasedTicketsModel() throws SQLException, IOException {
        purchasedTicketsManager = new PurchasedTicketsManager();

        purchasedTicketsToBeViewed = FXCollections.observableArrayList();
        purchasedTicketsToBeViewed.addAll(purchasedTicketsManager.getAllPurchasedTickets());
    }

    public ObservableList<PurchasedTickets> getObservablePurchasedTickets() {
        return purchasedTicketsToBeViewed;
    }

    public void createPurchasedTickets(int reservationsId, int ticketTypeId, int eventId, String qrCode, int quantity) throws IOException {
        PurchasedTickets purchasedTickets = new PurchasedTickets(reservationsId, ticketTypeId, eventId, qrCode, quantity);

        purchasedTicketsManager.createPurchasedTickets(purchasedTickets);
        purchasedTicketsToBeViewed.add(purchasedTickets);
    }

    public void deletePurchasedTickets(PurchasedTickets purchasedTickets) throws IOException {
        purchasedTicketsManager.deletePurchasedTickets(purchasedTickets);
        purchasedTicketsToBeViewed.remove(purchasedTickets);
    }

}
