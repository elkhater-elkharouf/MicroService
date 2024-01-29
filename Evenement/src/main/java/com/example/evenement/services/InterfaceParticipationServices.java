package com.example.evenement.services;

import com.example.evenement.entites.Participation;

import java.security.Principal;
import java.util.List;

public interface InterfaceParticipationServices {
    public List<Participation> getAllParticipations();
    public Participation addParticipation(int idEvent);
    public Participation updateParticipation(Participation participation);
    public void deleteParticipation(int id);
    public Participation getParticipationById(int id );
    public Participation etatParticipation(int idParticipation, int etat ,  String participantPhoneNumber ) ;

    public List<Participation> participationParUser(int iduser);
    public List<Participation> listParticipationParEvent( int idEvent);




}
