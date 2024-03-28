package BE;

import java.sql.Time;
import java.time.LocalDateTime;

public class Event {

    private int id;
    private int statusId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Time startTime;
    private Time endTime;
    private String description;
    private String createdBy;
    private String assignedBy;
    private LocalDateTime dateApproval;

    public Event(int id, int statusId, String title, LocalDateTime startDate, LocalDateTime endDate
            , Time startTime, Time endTime, String description, String createdBy, String assignedBy, LocalDateTime dateApproval) {
        this.id = id;
        this.statusId = statusId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.createdBy = createdBy;
        this.assignedBy = assignedBy;
        this.dateApproval = dateApproval;
    }

    public Event(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    //Skal vi nok ikke bruge, bliver der lige for nu.
    public void setId(int id) {
        this.id = id;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public LocalDateTime getDateApproval() {
        return dateApproval;
    }

    public void setDateApproval(LocalDateTime dateApproval) {
        this.dateApproval = dateApproval;
    }



}
