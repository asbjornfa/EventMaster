package GUI.Model;

import BE.Event;
import BE.User;
import BLL.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventModel {

    private ObservableList<Event> eventsToBeViewed;
    private EventManager eventManager;

    public EventModel() throws Exception{
        eventManager = new EventManager();
        eventsToBeViewed = FXCollections.observableArrayList();
        eventsToBeViewed.addAll(eventManager.getAllEvents());
    }

    public ObservableList<Event> getObservableEvents() {

        return eventsToBeViewed;
    }

    public void createEvent(String title, String location, LocalDateTime startDate, LocalDateTime endDate,
                            LocalTime startTime, LocalTime endTime, String description) throws IOException {

        Event event = new Event(title, location, startDate, endDate, startTime, endTime, description);

        eventManager.createEvent(event);

        eventsToBeViewed.add(event);


    }

    public void deleteEvent(Event event) throws IOException {
        eventManager.deleteEvent(event);

        eventsToBeViewed.remove(event);
    }

}
