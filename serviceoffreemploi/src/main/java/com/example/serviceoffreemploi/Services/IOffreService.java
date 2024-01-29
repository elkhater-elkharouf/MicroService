package com.example.serviceoffreemploi.Services;

import com.example.serviceoffreemploi.Entities.Offre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOffreService {
    public List<Offre> getAllOffres();
    public Offre getOffreById(int idOffre);
    public ResponseEntity<Offre> addOfre(Offre offre);
    public Offre updateOffre(Offre offre);
    public void deleteOffre(int idOffre);
}
