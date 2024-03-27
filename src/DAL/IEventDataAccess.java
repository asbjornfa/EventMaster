package DAL;

import BE.Event;

import java.io.IOException;
import java.util.List;

public interface IEventDataAccess {

    public List<Event> getAllEvents() throws IOException;
    public Event createEvent(Event event) throws IOException;
    public Event updateEvent(Event event) throws IOException;
    public Event deleteEvent(Event event) throws IOException;
}
