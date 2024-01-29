package com.example.evenement.repositories;

import com.example.evenement.entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository  extends JpaRepository<Event, Integer> {
    @Query("select e from Event e where e.etatEvent = :etat")
    List<Event> findEventByEtatEvent(@Param("etat") int etat );


}
