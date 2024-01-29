package com.example.quiz.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String questionTitle;
    private int questionScore;
    private String domaineName ;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Option> options;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Reponse> reponses;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Domaine domaine ;
}
