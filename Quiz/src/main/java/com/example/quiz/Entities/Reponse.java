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
public class Reponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String userAnswer;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Question question;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> users ;

}
