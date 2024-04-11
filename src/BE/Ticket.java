package BE;

public class Ticket {
    private int id;
    private int price;
    private int ticketTypeId;
    private int eventId;
    private int quantityAvailable;


    public Ticket(int id, int price, int ticketTypeId, int eventId, int quantityAvailable) {
        this.id = id;
        this.price = price;
        this.ticketTypeId = ticketTypeId;
        this.eventId = eventId;
        this.quantityAvailable = quantityAvailable;
    }

    public Ticket(int price, int ticketTypeId, int eventId, int quantityAvailable) {
        this.price = price;
        this.ticketTypeId = ticketTypeId;
        this.eventId = eventId;
        this.quantityAvailable = quantityAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
