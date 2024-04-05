package BE;

public class Ticket {
        private int id;
        private int price;
        private String ticket_layout;
        private String ticket_description;

    public Ticket(int id, int price, String ticket_layout, String ticket_description) {
        this.id = id;
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
    }
    public Ticket( int price, String ticket_layout, String ticket_description) {
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
    }

    public int getId() {
        return id;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTicket_layout() {
        return ticket_layout;
    }

    public void setTicket_layout(String ticket_layout) {
        this.ticket_layout = ticket_layout;
    }

    public String getTicket_description() {
        return ticket_description;
    }

    public void setTicket_description(String ticket_description) {
        this.ticket_description = ticket_description;
    }


}


