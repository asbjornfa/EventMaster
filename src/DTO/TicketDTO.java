package DTO;

import java.sql.Time;
import java.time.LocalDate;

public class TicketDTO {
    private int id;
    private double price;

    private LocalDate startDate;

    private Time startTime;
    private int eventId;

    private int ticketTypeId;

    private String qrCode;

    public TicketDTO(int id, double price, LocalDate startDate, Time startTime, int eventId, int ticketTypeId, String qrCode) {
        this.id = id;
        this.price = price;
        this.startDate = startDate;
        this.startTime = startTime;
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.qrCode = qrCode;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }


}
