package com.example.produitcategorie.services;

import com.example.produitcategorie.entities.Categorie;

import java.util.List;

public interface ICategorie {

    Categorie AddCategorie(Categorie categorie);
    Categorie UpdateCategorie(int idCategorie, Categorie categorie);
    void deleteCategorie(int idCategorie);
    List<Categorie> allCategories();
    Categorie findCategorieById(int id);

}
