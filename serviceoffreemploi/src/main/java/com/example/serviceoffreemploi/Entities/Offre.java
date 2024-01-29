package com.example.serviceoffreemploi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String titre;
    private String description;

    private String typecontrat;
    private int salaire;

    private int statut;
    private String location;


@Temporal(TemporalType.DATE)
    private Date datepubilcation;

    @Temporal(TemporalType.DATE)
    private Date datelimite;



    @OneToMany(mappedBy = "offre" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Candidature> candidatureList;





    @ManyToMany(mappedBy = "offres" )
    private Set<Competence> competences;

}
