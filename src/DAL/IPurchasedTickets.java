package DAL;


import BE.PurchasedTickets;

import java.io.IOException;
import java.util.List;

public interface IPurchasedTickets {

    public List<PurchasedTickets> getAllPurchasedTickets() throws IOException;
    public PurchasedTickets createPurchasedTickets(PurchasedTickets purchasedTickets) throws IOException;
    public void deletePurchasedTickets(PurchasedTickets purchasedTickets) throws IOException;
}


