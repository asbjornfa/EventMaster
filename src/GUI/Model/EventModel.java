package GUI.Model;

import BE.Event;
import BLL.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;

public class EventModel {

    private ObservableList<Event> eventsToBeViewed;
    private EventManager eventManager;

    public EventModel() throws Exception{
        eventManager = new EventManager();
        eventsToBeViewed = FXCollections.observableArrayList();
        eventsToBeViewed.addAll(eventManager.getAllEvents());
    }

    public ObservableList<Event> getEventsToBeViewed() {
        return eventsToBeViewed;
    }

    public void createEvent(String title, String location, LocalDateTime startDate, LocalDateTime endDate,
                            Time startTime, Time endTime, String description) throws IOException {
        Event event = new Event(title, location, startDate, endDate, startTime, endTime, description);

        eventManager.createEvent(event);

        eventsToBeViewed.add(event);
    }
}
