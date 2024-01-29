package com.example.serviceoffreemploi.Services;

import com.example.serviceoffreemploi.Entities.Candidature;
import com.example.serviceoffreemploi.Entities.CandidatureFile;
import com.example.serviceoffreemploi.Entities.Offre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;




import org.springframework.core.io.Resource;
public interface ICandidatureService {
    public List<Candidature> getAllCandidature();
    public Candidature getCandidatureById(int idCandidature);
    public Candidature addCandidature(String motivation, MultipartFile[] files, int idOffre, String idUser);
    public Candidature addCandidatureWithFiles(Candidature candidature );
    public Candidature updateCandidature(Candidature candidature);
    public void deleteCandidature(int idCandidature);

    public List<Candidature> listCandidatureByOffre(int idOffre);

    public ResponseEntity<Resource> getFileCandidature(int idFile);


    public Candidature getCandidatureByUserAndOffre(int idUser , int idOffre);
}
