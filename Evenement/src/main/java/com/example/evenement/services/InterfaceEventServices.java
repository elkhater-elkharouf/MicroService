package com.example.evenement.services;

import com.example.evenement.entites.Event;

import java.util.List;

public interface InterfaceEventServices {
    public List<Event> getAllEvents();
    public Event addEvent(Event event);
    public Event updateEvent(Event event);
    public void deleteEvent(int id);
    public Event getEventById(int id );
    public Event EtatEvent(int id) ;

    public List<Event> getAllEventsByEtat(int etat);
    public List<Event> getAllEventsCards() ;






    }
