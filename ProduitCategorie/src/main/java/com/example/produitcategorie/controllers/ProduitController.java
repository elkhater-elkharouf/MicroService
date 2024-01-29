package com.example.produitcategorie.controllers;


import com.example.produitcategorie.entities.Produit;
import com.example.produitcategorie.services.CategoryStatisticsImageService;
import com.example.produitcategorie.services.IProduit;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.util.annotation.Nullable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProduitController {

    IProduit iProduit;


    @PostMapping(path = "/admin/add/{cate}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produit> createProduit(@PathVariable int cate,@RequestParam String nom,@RequestParam double prix ,@RequestParam String descr,@RequestParam boolean dispo,@RequestParam MultipartFile image) throws IOException {
        return new ResponseEntity<>(iProduit.AddProduit(cate,nom,prix,descr,dispo,image), HttpStatus.OK);
    }


    @PutMapping(value = "/admin/update/{id}/{idcat}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produit> updateProduit(@PathVariable(value = "idcat") int idcat,@PathVariable(value = "id") int id, @RequestParam String nom,@RequestParam double prix ,@RequestParam String descr,@RequestParam boolean dispo) {
        return new ResponseEntity<>(iProduit.UpdateProduit(idcat,id, nom,prix,descr,dispo),
                HttpStatus.OK);
    }

    @PutMapping(value = "/admin/updateImage/{idProduit}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produit> UpdateImage(@PathVariable int idProduit, @RequestParam MultipartFile image) throws IOException {
        return new ResponseEntity<>(iProduit.UpdateImage(idProduit, image),
                HttpStatus.OK);
    }

    @GetMapping("/admin/image")
    public ResponseEntity<byte[]> getCategoryStatisticsImage() {
        Map<String, Long> categoryStatistics = iProduit.getCategoryStatistics();
        byte[] imageBytes = CategoryStatisticsImageService.generateCategoryStatisticsImage(categoryStatistics);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }


        @DeleteMapping(value = "/admin/deleteP/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduit(@PathVariable(value = "id") int id) {
        iProduit.deleteProduit(id);
    }

    @GetMapping(value = "/user/getAllP", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Produit> getAllProduits() {
        return iProduit.allProduits();
    }

    @GetMapping(value = "/user/getByIDP/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produit> getByIDProduit(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(iProduit.findProduitById(id),
                HttpStatus.OK);
    }

    @GetMapping(value="/user/Filter", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Produit> FilterProduits(@RequestParam String name,@RequestParam String categorie,@RequestParam double MaxBudget,@RequestParam double MinBudget) {
        return iProduit.FilterProduitss(name, categorie, MaxBudget, MinBudget);
    }

    @GetMapping("/admin/{productId}/qrcode")
    public void generateQRCode(@PathVariable int productId, HttpServletResponse response) throws IOException, WriterException {
        byte[] qrCode = iProduit.generateQRCode(productId);

        response.setContentType("image/png");
        response.getOutputStream().write(qrCode);
    }
}
