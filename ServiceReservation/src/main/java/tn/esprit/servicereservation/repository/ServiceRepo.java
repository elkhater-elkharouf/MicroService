package tn.esprit.servicereservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.servicereservation.entity.Service;

import java.util.List;

public interface ServiceRepo extends JpaRepository<Service,Integer> {

    List<Service> findTop3ByOrderByReservationsDesc();

    @Query("SELECT s FROM Service s WHERE s.reservations IS EMPTY")
    List<Service> findUnreservedServices();

}
