package GUI.Model;

import BE.Reservations;
import BLL.ReservationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class ReservationModel {
    private ObservableList<Reservations> reservationsToBeViewed;
    private ReservationManager reservationManager;

    public ReservationModel() throws Exception{
        reservationManager = new ReservationManager();
        reservationsToBeViewed = FXCollections.observableArrayList();
        reservationsToBeViewed.addAll(reservationManager.getAllReservations());
    }

    public ObservableList<Reservations> getObservableReservations() {
        return reservationsToBeViewed;
    }

    public Reservations createReservation(String email) throws IOException {

        Reservations reservations = new Reservations(email);

        reservationManager.createReservation(reservations);
        reservationsToBeViewed.add(reservations);

        return reservations;
    }

    public void deleteReservation(int reservationId) throws IOException {
        reservationManager.deleteReservation(reservationId);
        reservationsToBeViewed.remove(reservationId);
    }
}
