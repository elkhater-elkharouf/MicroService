package tn.esprit.servicereservation.service;

import tn.esprit.servicereservation.entity.Reservation;

import java.util.List;

public interface IReservation {

    Reservation AddReservation(Reservation reservation);
    Reservation UpdateReservation(int idReservation,Reservation reservation);
    void deleteReservation(int idReservation);
    List<Reservation> allReservation();
    Reservation findReservationById(int id);
    List<Reservation> allReservationByOwner(int idOwner);
    List<Reservation> allReservationByService(int idService);

}
