package tn.esprit.servicereservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.servicereservation.entity.Reservation;

public interface ReservationRepo  extends JpaRepository<Reservation,Integer> {
}
