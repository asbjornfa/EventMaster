package BLL;

import BE.Event;
import BE.Reservations;
import DAL.DB.EventDAO_DB;
import DAL.DB.ReservationDAO_DB;
import DAL.IEventDataAccess;
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

    public void deleteReservation(Reservations reservations) throws IOException {

        reservationsDAO.deleteReservation(reservations);
        System.out.println("Manager");
    }
}
