package com.example.serviceoffreemploi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.xstream.annotations.XStreamInclude;
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
public class Candidature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Temporal(TemporalType.DATE)
    private Date datepostlation;

    private int etatcandidature;

    private String motivation;

    @ManyToOne
    private Offre offre;

    private String idUser;



    @OneToMany(mappedBy = "candidature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CandidatureFile> candidatureFiles;


}
