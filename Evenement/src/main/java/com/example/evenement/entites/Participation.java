package com.example.evenement.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idUser;
    private String participationName;
    private int etatParticipation;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;



    // Getters and setters


}