package BE;

public class Ticket {
        private int id;
        private int price;
        private String ticket_layout;
        private String ticket_description;
        private int ticket_typeId;
        private String title;



    public Ticket(int id, int price, String ticket_layout, String ticket_description) {
        this.id = id;
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
    }
    public Ticket( int price, String ticket_layout, String ticket_description, String title) {
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
        this.title = title;
    }
    public Ticket( int price, String ticket_layout, String ticket_description, int ticket_typeId) {
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
        this.ticket_typeId = ticket_typeId;
    }

    public Ticket( int price, String ticket_description, int ticket_typeId) {
        this.price = price;
        this.ticket_description = ticket_description;
        this.ticket_typeId = ticket_typeId;
    }
    public Ticket( int id, int price, String ticket_layout, String ticket_description, int ticket_typeId) {
        this.id = id;
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
        this.ticket_typeId = ticket_typeId;
    }

    public Ticket( int price, String ticket_layout, String ticket_description, String title, int ticket_typeId) {
        this.id = id;
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
        this.title = title;
        this.ticket_typeId = ticket_typeId;
    }

    public Ticket( int id, int ticket_typeId,int price, String ticket_layout, String ticket_description, String title) {
        this.id = id;
        this.ticket_typeId = ticket_typeId;
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
        this.title = title;

    }
    public Ticket( int id,int price, String ticket_layout, String ticket_description, String title) {
        this.id = id;
        this.price = price;
        this.ticket_layout = ticket_layout;
        this.ticket_description = ticket_description;
        this.title = title;

    }



    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTicket_typeId() {
        return ticket_typeId;
    }

    public void setTicket_typeId(int ticket_typeId) {
        this.ticket_typeId = ticket_typeId;
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


