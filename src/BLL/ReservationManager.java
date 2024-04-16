package BLL;

import BE.Reservations;
import DAL.DB.ReservationDAO_DB;
import DAL.IReservations;

import java.io.IOException;
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

    public void deleteReservation(int reservationId) throws IOException {
        reservationsDAO.deleteReservation(reservationId);
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
