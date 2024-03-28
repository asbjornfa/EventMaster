package GUI.Model;

import BE.Event;
import BLL.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

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

    public void createEvent(String title) throws IOException {
        Event event = new Event(title);

        eventManager.createEvent(event);

        eventsToBeViewed.add(event);
    }
}
