package BLL;

import BE.Event;
import BE.Reservations;
import DAL.DB.EventDAO_DB;
import DAL.DB.ReservationDAO_DB;
import DAL.IEventDataAccess;
import DAL.IReservations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReservationManager {
    private IReservations reservationsDAO;

    public ReservationManager() throws IOException {
        try{
            reservationsDAO = new ReservationDAO_DB();

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public List<Reservations> getAllReservations() throws IOException{
        try {
            return reservationsDAO.getAllReservations();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public Reservations createReservation(Reservations newReservation) throws IOException{
        try {
            return reservationsDAO.createReservation(newReservation);
        }catch (Exception e) {
            throw new IOException(e);
        }

    }

    public void deleteReservation(Reservations reservations) throws IOException {

        reservationsDAO.deleteReservation(reservations);
        System.out.println("Manager");
    }

    public Reservations getReservationByEmail(String email) throws IOException {
        try {
            List<Reservations> allReservations = reservationsDAO.getAllReservations();
            for (Reservations reservation : allReservations) {
                if (reservation.getEmail().equalsIgnoreCase(email)) {
                    return reservation;
                }
            }
            return null; // Return null if no matching reservation is found
        } catch (IOException e) {
            throw new IOException("Failed to get reservation by email.", e);
        }
    }

}
