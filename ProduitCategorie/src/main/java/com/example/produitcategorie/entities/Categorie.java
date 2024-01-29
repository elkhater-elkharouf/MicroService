package com.example.produitcategorie.entities;

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
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie ;

    private String nomcategorie;

    @OneToMany(mappedBy = "categorie" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Produit> produits;
}
