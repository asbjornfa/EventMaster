package BLL;

import BE.Event;
import BE.User;
import DAL.DB.EventDAO_DB;
import DAL.IEventDataAccess;

import java.io.IOException;
import java.util.List;

public class EventManager {

    private IEventDataAccess eventDAO;

    public EventManager() throws IOException{
        try{
            eventDAO = new EventDAO_DB();

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public List<Event> getAllEvents() throws IOException{
        try {
            return eventDAO.getAllEvents();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public Event createEvent(Event newEvent) throws IOException{
        try {
            return eventDAO.createEvent(newEvent);
        }catch (Exception e) {
            throw new IOException(e);
        }

    }

    public void deleteEvent(Event event) throws IOException {

        eventDAO.deleteEvent(event);
    }
}
