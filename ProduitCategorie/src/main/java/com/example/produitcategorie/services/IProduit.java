package com.example.produitcategorie.services;

import com.example.produitcategorie.entities.Categorie;
import com.example.produitcategorie.entities.Produit;
import com.google.zxing.WriterException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IProduit {

    Produit AddProduit(int cate,String nom,double prix , String descr,boolean dispo , MultipartFile image) throws IOException;
    Produit UpdateProduit(int idcat,int idProduit, String nom,double prix , String descr,boolean dispo );

    Produit UpdateImage(int idProduit,MultipartFile image) throws IOException;
    void deleteProduit(int idProduit);

    List<Produit> allProduits();
    Produit findProduitById(int id);

    public List<Produit> FilterProduitss(String name , String category , double MaxBudget , double MinBudget);

    public Map<String, Long> getCategoryStatistics();
    public byte[] generateQRCode(int productId) throws WriterException, IOException;


}
