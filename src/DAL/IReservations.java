package DAL;

import BE.Event;
import BE.Reservations;

import java.io.IOException;
import java.util.List;

public interface IReservations {

    public List<Reservations> getAllReservations() throws IOException;
    public Reservations createReservation(Reservations reservations) throws IOException;
    public Reservations deleteReservation(Reservations reservations) throws IOException;
}
