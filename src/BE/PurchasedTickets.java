package BE;

public class PurchasedTickets {
    private int id;
    private int reservationId;
    private int ticketTypeId;
    private int eventId;
    private String qrCode;
    private int quantity;
    private String eventTitle;
    private String ticketTypeTitle;
    private String emailString;
    private String eventLocation;
    private String startDate;
    private String startTime;

    public PurchasedTickets(int id, int reservationId, int ticketTypeId, int eventId, String qrCode, int quantity, String eventTitle, String ticketTypeTitle, String emailString, String eventLocation, String startDate, String startTime) {
        this.id = id;
        this.reservationId = reservationId;
        this.ticketTypeId = ticketTypeId;
        this.eventId = eventId;
        this.qrCode = qrCode;
        this.quantity = quantity;
        this.eventTitle = eventTitle;
        this.ticketTypeTitle = ticketTypeTitle;
        this.emailString = emailString;
        this.eventLocation = eventLocation;
        this.startDate = startDate;
        this.startTime = startTime;
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

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getTicketTypeTitle() {
        return ticketTypeTitle;
    }

    public void setTicketTypeTitle(String ticketTypeTitle) {
        this.ticketTypeTitle = ticketTypeTitle;
    }

    public String getEmailString() {
        return emailString;
    }

    public void setEmailString(String emailString) {
        this.emailString = emailString;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
