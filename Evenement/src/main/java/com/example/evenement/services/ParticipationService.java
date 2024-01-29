package com.example.evenement.services;

import com.example.evenement.entites.Event;
import com.example.evenement.entites.Participation;
import com.example.evenement.repositories.EventRepository;
import com.example.evenement.repositories.ParticipationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class ParticipationService  implements InterfaceParticipationServices {

    ParticipationRepository participationRepository;
    EventRepository eventRepository;
       NotificationService notificationService;
    @Override
    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    @Override
    public Participation addParticipation(int idEvent ) {

        Event e = eventRepository.findById(idEvent).orElse(null);
        Participation p = new Participation();
        // 0 etat  Participation Envoyer
        p.setParticipationName("Malek");
        p.setIdUser(1);
        p.setEtatParticipation(0);
        p.setEvent(e);
        return participationRepository.save(p);

    }

    @Override
    public Participation updateParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    @Override
    public void deleteParticipation(int id) {
        participationRepository.deleteById(id);
    }

    @Override
    public Participation getParticipationById(int id) {
        return participationRepository.findById(id).orElse(null);
    }

    @Override
// participe a un evenement
    public Participation etatParticipation(int idParticipation, int etat, String participantPhoneNumber  ) {

         int nb;
        Participation participation = participationRepository.findById(idParticipation).orElse(null);
        String googleMapsLink = "https://www.google.com/maps/?q=";

        // User user = userRepository.findByEmail(principal.getName()) ;
        if (etat == 1) {

            notificationService.sendNotification(participantPhoneNumber,
                    "Votre participation a été acceptée ! La date de l'événement est " + participation.getEvent().getEventDate()
                            + ". Vous pouvez voir l'emplacement de l'événement ici: " + googleMapsLink);

            nb  = participation.getEvent().getNombreParticipation();
            participation.getEvent().setNombreParticipation(nb-1);
            participation.setEtatParticipation(etat);
            if(nb<=1)
            {
                // etat evenet Complet
               participation.getEvent().setEtatEvent(1);

            }
        } else if (etat == 2) {
            notificationService.sendNotification(participantPhoneNumber, "Désolé, votre participation a été refusée.");

            //refuser
            participation.setEtatParticipation(etat);


        } else if (etat == 3) {

            deleteParticipation(idParticipation);


    }
      return participationRepository.save(participation);
}

    @Override

    public List<Participation> participationParUser(int iduser){
       return  participationRepository.participationUser(iduser);



    }
    @Override

    public List<Participation> listParticipationParEvent( int idEvent)
    {
       return participationRepository.listParticipationParEvent(idEvent);

    }
}
