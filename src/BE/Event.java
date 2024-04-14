package BE;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event {

    private int id;
    private int statusId;
    private String title;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private Time startTime;
    private Time endTime;
    private String description;
    private String createdBy;
    private String assignedBy;
    private LocalDateTime dateApproval;
    private String imagePath;
    private String coordinators;



    public Event(int id, String title, String location, LocalDate startDate, LocalDate endDate,
                 Time startTime, Time endTime, String description, String createdBy, String imagePath, String coordinators) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.createdBy = createdBy;
        this.imagePath = imagePath;
        this.coordinators = coordinators;
    }

    public Event(String title, String location, LocalDate startDate, LocalDate endDate,
                 Time startTime, Time endTime, String description, String imagePath) {
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.imagePath = imagePath;
    }
    public Event(String title, LocalDate startDate) {
        this.title = title;
        this.startDate = startDate;
    }

    public Event(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Event(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCoordinators() {
        return coordinators;
    }

    public void setCoordinators(String coordinators) {
        this.coordinators = coordinators;
    }
}
