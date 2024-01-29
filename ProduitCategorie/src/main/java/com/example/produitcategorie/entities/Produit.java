package com.example.produitcategorie.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import reactor.util.annotation.Nullable;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit ;

    private String nomproduit;

    private String description;

    @Lob
    private String image;

    private double prix;

    private boolean disponible;

    @ManyToOne()
    private Categorie categorie;

    public String generateQRCodeContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.nomproduit).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        sb.append("Price: ").append(this.prix).append("\n");
        sb.append("Status: ").append(this.disponible).append("\n");
        sb.append("Categorie: ").append(this.categorie.getNomcategorie()).append("\n");
        return sb.toString();
    }
}
