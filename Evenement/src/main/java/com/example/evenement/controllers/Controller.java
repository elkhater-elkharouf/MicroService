package com.example.evenement.controllers;

import com.example.evenement.entites.Event;
import com.example.evenement.entites.Participation;
import com.example.evenement.services.InterfaceEventServices;
import com.example.evenement.services.InterfaceParticipationServices;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController

@CrossOrigin(origins = "*")
public class Controller {
    /// participation
    InterfaceParticipationServices interfaceParticipationServices ;
    InterfaceEventServices interfaceEventServices;
    @GetMapping("/admin/getAllParticipations")
    public List<Participation> getAllParticipations(){
        return interfaceParticipationServices.getAllParticipations();
    }
    @PostMapping("/user/addParticipation/{idEvent}")
    @ResponseBody
    public Participation addParticipation(@PathVariable("idEvent") int idEvent) {
        return interfaceParticipationServices.addParticipation(idEvent);
    }
    @PutMapping("/updateParticipation")
    @ResponseBody
    public Participation updateParticipation( @RequestBody Participation c) {
        return interfaceParticipationServices.updateParticipation(c);
    }
    @DeleteMapping("/deleteParticipation/{id}")
    public void deleteParticipation( @PathVariable("id") int id) {
        interfaceParticipationServices.deleteParticipation(id);

    }
    //marche


    @GetMapping("/getParticipationById/{id}")
    public Participation getParticipationById( @PathVariable("id") int id) {
        return interfaceParticipationServices.getParticipationById(id);

    }
    @GetMapping("/user/getEventByEtat/{etat}")
    public List<Event> getAllEventsByEtat( @PathVariable("etat") int etat){
        return interfaceEventServices.getAllEventsByEtat(etat);
    }
    @GetMapping("/user/mesParticipation/{idUser}")
    public List<Participation> participationParUser( @PathVariable("idUser") int idUser){
        return interfaceParticipationServices.participationParUser(idUser);


    }
    @GetMapping("/admin/ParticipationEvent/{idEvent}")
    public List<Participation> listParticipationParEvent(@PathVariable("idEvent") int idEvent)
    {

        return interfaceParticipationServices.listParticipationParEvent(idEvent);
    }

    /// Event
    @GetMapping("/admin/getAllEvents")
    public List<Event> getAllEvents(){
        return interfaceEventServices.getAllEvents();
    }
    @GetMapping("/user/getAllEventsCards")
    public List<Event> getAllEventsCards(){
        return interfaceEventServices.getAllEventsCards();
    }

    @PostMapping(path = "/admin/addEvent", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public Event addEvent(
            @RequestParam("image") MultipartFile file,
            @RequestParam("eventName") String eventName,
            @RequestParam("eventDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date eventDate,
            @RequestParam("nombreParticipation") int nombreParticipation

    ) {

        Event event = new Event();
        event.setEventName(eventName);
        event.setEventDate(eventDate);
        event.setNombreParticipation(nombreParticipation);

        try {
            byte[] imageData = file.getBytes();
            event.setImageData(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return interfaceEventServices.addEvent(event);
    }
    /* @PostMapping("/addEvent")
    @ResponseBody
    public Event addEvent(@RequestBody Event c) {
        return interfaceEventServices.addEvent(c);
    }
    */

    @PutMapping("/admin/updateEvent")
    @ResponseBody
    public Event updateEvent( @RequestBody Event c) {
        return interfaceEventServices.updateEvent(c);
    }
    @DeleteMapping("/admin/deleteEvent/{id}")
    public void deleteEvent( @PathVariable("id") int id) {
        interfaceEventServices.deleteEvent(id);

    }
    @GetMapping("/user/getEventById/{id}")
    public Event getEventById( @PathVariable("id") int id) {
        return interfaceEventServices.getEventById(id);

    }
    // Accepter ou refuser participation
    @PutMapping("/admin/etatParticiaption/{idParticipant}/{etat}/{participantPhoneNumber}")
    @ResponseBody
    public void etatParticipation( @PathVariable("idParticipant") int idParticipant, @PathVariable("etat") int etat
    , @PathVariable("participantPhoneNumber") String participantPhoneNumber ) {
        interfaceParticipationServices.etatParticipation(idParticipant,etat,participantPhoneNumber );
    }

    //Marche
    @PutMapping("/admin/etatEvent/{idEvent}")
    @ResponseBody
    public void etatEvent( @PathVariable("idEvent") int idEvent) {
        interfaceEventServices.EtatEvent(idEvent);
    }





}