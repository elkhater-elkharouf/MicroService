package tn.esprit.servicereservation.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idService ;

    private String titre,description;
    private int owner;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<Reservation> reservations;
}
