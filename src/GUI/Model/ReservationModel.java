package GUI.Model;

import BE.Event;
import BE.Reservations;
import BLL.EventManager;
import BLL.ReservationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public void createReservation(String email) throws IOException {

        Reservations reservations = new Reservations(email);

        reservationManager.createReservation(reservations);

        reservationsToBeViewed.add(reservations);


    }

    public void deleteReservation(Reservations reservations) throws IOException {
        reservationManager.deleteReservation(reservations);

        reservationsToBeViewed.remove(reservations);
        System.out.println("model");
    }
}
