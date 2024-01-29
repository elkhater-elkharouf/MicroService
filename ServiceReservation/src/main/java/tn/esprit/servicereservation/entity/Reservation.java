package tn.esprit.servicereservation.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation ;

    private int owner;

    private Date dateReservation;

    @ManyToOne()
    private Service service;
}
