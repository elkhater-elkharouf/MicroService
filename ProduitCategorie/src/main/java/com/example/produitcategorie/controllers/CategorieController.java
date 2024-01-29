package com.example.produitcategorie.controllers;


import com.example.produitcategorie.entities.Categorie;
import com.example.produitcategorie.services.ICategorie;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategorieController {

ICategorie iCategorie;

    @PostMapping(value = "/admin/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        return new ResponseEntity<>(iCategorie.AddCategorie(categorie), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categorie> updateCategorie(@PathVariable(value = "id") int id, @RequestBody Categorie categorie) {
        return new ResponseEntity<>(iCategorie.UpdateCategorie(id, categorie),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidat(@PathVariable(value = "id") int id) {
        iCategorie.deleteCategorie(id);
    }

    @GetMapping(value = "/user/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Categorie> getAllCategories() {
        return iCategorie.allCategories();
    }

    @GetMapping(value = "/admin/getByID/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categorie> getByIDCategorie(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(iCategorie.findCategorieById(id),
                HttpStatus.OK);
    }


}
