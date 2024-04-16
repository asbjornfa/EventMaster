package DAL;

import BE.Event;
import BE.Reservations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IReservations {

    public List<Reservations> getAllReservations() throws IOException;

    public Reservations createReservation(Reservations reservations) throws IOException;
    public void deleteReservation(int reservationId) throws IOException;


}
