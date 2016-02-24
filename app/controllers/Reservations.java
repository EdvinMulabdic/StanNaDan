package controllers;

import models.Apartment;
import models.Reservation;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.apartmentReservations;

import java.util.List;

/**
 * Created by ajla on 23-Feb-16.
 */
public class Reservations extends Controller {



    public Result approveReservation(Integer reservationId) {
        Reservation.approveReservation(reservationId);

        Apartment apartment = Reservation.getApartmentByReservationId(reservationId);
        List<Reservation> reservations = Reservation.getReservationsByApartmentIdReservation(apartment.id);
        return ok(apartmentReservations.render(apartment, reservations));
    }

    public Result declineReservation(Integer reservationId) {
        Reservation.declineReservation(reservationId);

        Apartment apartment = Reservation.getApartmentByReservationId(reservationId);
        List<Reservation> reservations = Reservation.getReservationsByApartmentIdReservation(apartment.id);
        return ok(apartmentReservations.render(apartment, reservations));
    }
}
