package com.example.evenement.services;

import com.example.evenement.entites.Event;
import com.example.evenement.entites.Participation;
import com.example.evenement.repositories.EventRepository;
import com.example.evenement.repositories.ParticipationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService implements InterfaceEventServices {
    EventRepository eventRepository;
    ParticipationRepository participationRepository;
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public List<Event> getAllEventsCards() {
        return eventRepository.findAll();
    }
    @Override
    public Event addEvent(Event event) {
        //etat disponible
        event.setEtatEvent(0);
        return eventRepository.save(event);
    }


    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }


    @Override
    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }
    @Override
    public Event EtatEvent(int id) {
        Event e = eventRepository.findById(id).orElse(null) ;
        if (e.getEtatEvent()==2){

        e.setEtatEvent(0);
       return eventRepository.save(e) ;
        }
        else if (e.getEtatEvent()==0){
            e.setEtatEvent(2);
            return eventRepository.save(e);

        }
        return eventRepository.save(e);
    }

    @Override
    public List<Event> getAllEventsByEtat(int etat) {
        List<Event> events= eventRepository.findEventByEtatEvent(etat);
        return events;
    }


}
