package BE;

public class PurchasedTickets {
    private int id;
    private int reservationId;
    private int ticketTypeId;
    private int eventId;
    private String qrCode;
    private int quantity;

    public PurchasedTickets(int id, int reservationId, int ticketTypeId, int eventId, String qrCode, int quantity) {
        this.id = id;
        this.reservationId = reservationId;
        this.ticketTypeId = ticketTypeId;
        this.eventId = eventId;
        this.qrCode = qrCode;
        this.quantity = quantity;
    }

    public PurchasedTickets(int reservationId, int ticketTypeId, int eventId, String qrCode, int quantity) {
        this.reservationId = reservationId;
        this.ticketTypeId = ticketTypeId;
        this.eventId = eventId;
        this.qrCode = qrCode;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
