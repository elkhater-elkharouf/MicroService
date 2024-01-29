package com.example.evenement.repositories;

import com.example.evenement.entites.Event;
import com.example.evenement.entites.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository  extends JpaRepository<Participation, Integer> {

    @Query("select p from Participation p where p.idUser = :idUser")
    List<Participation> participationUser(@Param("idUser") int idUser );

    @Query("select p from Participation p,Event e where p.event.id = :idEvent")
    List<Participation> listParticipationParEvent(@Param("idEvent") int idEvent);
}
