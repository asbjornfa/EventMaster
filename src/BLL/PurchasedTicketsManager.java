package BLL;

import BE.PurchasedTickets;
import BE.User;
import DAL.DB.PurchasedTicketsDAO_DB;
import DAL.DB.UserDAO_DB;
import DAL.IPurchasedTickets;
import DAL.IUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PurchasedTicketsManager {

    private IPurchasedTickets purchasedTicketsDAO;

    public PurchasedTicketsManager() throws SQLException {
        purchasedTicketsDAO = new PurchasedTicketsDAO_DB();
    }

    public List<PurchasedTickets> getAllPurchasedTickets() throws IOException {
        return purchasedTicketsDAO.getAllPurchasedTickets();
    }

    public PurchasedTickets createPurchasedTickets(PurchasedTickets purchasedTickets) throws IOException {
        return purchasedTicketsDAO.createPurchasedTickets(purchasedTickets);
    }

    public void deletePurchasedTickets(PurchasedTickets purchasedTickets) throws IOException {
        purchasedTicketsDAO.deletePurchasedTickets(purchasedTickets);
    }
}

