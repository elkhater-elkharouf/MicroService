package com.example.evenement.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String eventName;
    @Column(name = "image_data", columnDefinition="BLOB")
    private byte[] imageData;
    private int nombreParticipation;
    private Date eventDate;
    private String address;
    private Double latitude;
    private Double longitude;
    private int etatEvent;
    @OneToMany(mappedBy = "event")
    private List<Participation> participations;




}