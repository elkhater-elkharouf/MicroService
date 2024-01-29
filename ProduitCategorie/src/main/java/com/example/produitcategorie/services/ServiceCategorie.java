package com.example.produitcategorie.services;


import com.example.produitcategorie.entities.Categorie;
import com.example.produitcategorie.repositories.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceCategorie implements ICategorie{

    CategorieRepository categorieRepository;

    @Override
    public Categorie AddCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie UpdateCategorie(int idCategorie, Categorie categorie) {
        Categorie oldCategorie =this.categorieRepository.findById(idCategorie).orElse(null);
        if (oldCategorie!=null){
            categorie.setIdCategorie(idCategorie);
            return this.categorieRepository.save(categorie);
        }
        return null;
    }

    @Override
    public void deleteCategorie(int idCategorie) {
        categorieRepository.deleteById(idCategorie);
    }

    @Override
    public List<Categorie> allCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie findCategorieById(int id) {
        return categorieRepository.findById(id).orElse(null);
    }
}
