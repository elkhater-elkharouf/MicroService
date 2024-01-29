package com.example.produitcategorie.services;


import com.example.produitcategorie.entities.Categorie;
import com.example.produitcategorie.entities.Produit;
import com.example.produitcategorie.repositories.CategorieRepository;
import com.example.produitcategorie.repositories.ProduitRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceProduit implements IProduit{

    ProduitRepository produitRepository;
    CategorieRepository categorieRepository;

    @Override
    public Produit AddProduit(int cate,String nom,double prix , String descr,boolean dispo,MultipartFile image) throws IOException {
        Produit p = new Produit();
        Categorie cat = categorieRepository.findById(cate).orElse(null);
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        p.setPrix(prix);
        p.setDisponible(dispo);
        p.setCategorie(cat);
        p.setNomproduit(nom);
        p.setDescription(descr);
        p.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        return produitRepository.save(p);
    }

    @Override
    public Produit UpdateProduit(int idcat,int idProduit,String nom,double prix , String descr,boolean dispo ) {
        Produit oldProduit =this.produitRepository.findById(idProduit).orElse(null);
        Categorie cat = this.categorieRepository.findById(idcat).orElse(null);
        if (oldProduit!=null){

            oldProduit.setCategorie(cat);
            oldProduit.setDescription(descr);
            oldProduit.setPrix(prix);
            oldProduit.setNomproduit(nom);
            oldProduit.setDisponible(dispo);
            return this.produitRepository.save(oldProduit);
        }
        return null;
    }

    @Override
    public Map<String, Long> getCategoryStatistics() {
        List<Produit> produits = produitRepository.findAll();
        Map<String, Long> categoryStatistics = produits.stream()
                .collect(Collectors.groupingBy(p -> p.getCategorie().getNomcategorie(), Collectors.counting()));
        return categoryStatistics;
    }

    @Override
    public Produit UpdateImage(int idProduit, MultipartFile image) throws IOException {
        Produit oldProduit =this.produitRepository.findById(idProduit).orElse(null);
        if (oldProduit!=null){
            String filename = StringUtils.cleanPath(image.getOriginalFilename());
            if(filename.contains("..")){
                System.out.println("!!! Not a valid File");
            }
            oldProduit.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            return this.produitRepository.save(oldProduit);

        }
    return null;
    }

    @Override
    public void deleteProduit(int idProduit) {
        produitRepository.deleteById(idProduit);
    }

    @Override
    public List<Produit> allProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit findProduitById(int id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> FilterProduitss(String name, String category, double MaxBudget, double MinBudget) {
        List<Produit> produits = produitRepository.findAll();
        List<Produit> produits1 = new ArrayList<>();

        if(!name.equals("")){
            produits1 = produits.stream().filter(pr -> pr.getNomproduit().equals(name)).collect(Collectors.toList());
            if(!category.equals("")){
                produits1 = produits1.stream().filter(pr -> pr.getCategorie().getNomcategorie().equals(category)).collect(Collectors.toList());
            }
            if(MaxBudget != 0.0){
                produits1 = produits1.stream().filter(pr -> pr.getPrix()<=MaxBudget).collect(Collectors.toList());
            } if(MinBudget != 0.0){
                produits1 = produits1.stream().filter(pr -> pr.getPrix()>=MinBudget).collect(Collectors.toList());
            }
        }else if(!category.equals("")){
            produits1 = produits.stream().filter(pr -> pr.getCategorie().getNomcategorie().equals(category)).collect(Collectors.toList());
            if(MaxBudget != 0.0){
                produits1 = produits1.stream().filter(pr -> pr.getPrix()<=MaxBudget).collect(Collectors.toList());
            }
            if(MinBudget != 0.0){
                produits1 = produits1.stream().filter(pr -> pr.getPrix()>=MinBudget).collect(Collectors.toList());
            }
        }else if(MaxBudget != 0.0){
            produits1 = produits.stream().filter(pr -> pr.getPrix()<=MaxBudget).collect(Collectors.toList());
            if(MinBudget != 0.0){
                produits1 = produits1.stream().filter(pr -> pr.getPrix()>=MinBudget).collect(Collectors.toList());
            }
        }else if(MinBudget != 0.0){
            produits1 = produits.stream().filter(pr -> pr.getPrix()>=MinBudget).collect(Collectors.toList());
        }

        return produits1;
    }

    @Override
    public byte[] generateQRCode(int productId) throws WriterException, IOException {
        Produit product = produitRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        String content = product.generateQRCodeContent();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, 350, 350,
                Collections.singletonMap(EncodeHintType.MARGIN, 0));

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
        return out.toByteArray();
    }


}
