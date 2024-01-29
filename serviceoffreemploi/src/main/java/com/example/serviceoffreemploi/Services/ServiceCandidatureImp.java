package com.example.serviceoffreemploi.Services;

import com.example.serviceoffreemploi.Entities.Candidature;
import com.example.serviceoffreemploi.Entities.CandidatureFile;
import com.example.serviceoffreemploi.Entities.Competence;
import com.example.serviceoffreemploi.Entities.Offre;
import com.example.serviceoffreemploi.Repositories.CandidatureFileRepository;
import com.example.serviceoffreemploi.Repositories.CandidatureRepository;
import com.example.serviceoffreemploi.Repositories.OffreRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@AllArgsConstructor
public class ServiceCandidatureImp implements ICandidatureService{

    CandidatureRepository candidatureRepository;
    CandidatureFileRepository candidatureFileRepository;
    @Override
    public List<Candidature> getAllCandidature() {
        return candidatureRepository.findAll();
    }

    @Override
    public Candidature getCandidatureById(int idCandidature) {
        return null;
    }

    OffreRepository offreRepository;

    @Override
    public Candidature addCandidature(String motivation, MultipartFile[] files, int idOffre, String idUser) {
        Candidature candidature = new Candidature();
        candidature.setMotivation(motivation);
        candidature.setIdUser(idUser);

        Offre offre = offreRepository.findById(idOffre).orElse(null);
        candidature.setOffre(offre);
        offre.getCandidatureList().add(candidature);
        offreRepository.save(offre);

        // Save the Candidature entity first
        candidatureRepository.save(candidature);

        Set<CandidatureFile> managedCandidatureFiles = new HashSet<>();

        for (MultipartFile file : files) {
            CandidatureFile candidatureFile = new CandidatureFile();
            candidatureFile.setFileName(file.getOriginalFilename());
            try {
                String uploadDir = "C:/Users/Kouki/Desktop/Esprit/5eme/Applications Web Distribuees/Project/spring/MicroService/serviceoffreemploi/src/uploads";
                Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

                Files.createDirectories(uploadPath);

                String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(uniqueFileName);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                candidatureFile.setStoredFileName(uniqueFileName);

                managedCandidatureFiles.add(candidatureFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set the reference to the Candidature entity
            candidatureFile.setCandidature(candidature);
            candidatureFileRepository.save(candidatureFile);

            // Add the managed CandidatureFile to the set
            managedCandidatureFiles.add(candidatureFile);
        }

        // Update the Candidature entity with the managed CandidatureFile set
        candidature.setCandidatureFiles(managedCandidatureFiles);

        // You can save the Candidature entity again if needed, but it might not be necessary

        return candidature;
    }


    @Override
    public Candidature addCandidatureWithFiles(Candidature candidature) {
        Set<CandidatureFile> candidatureFiles =  candidature.getCandidatureFiles();
        Set<CandidatureFile> managedCnadidaturesFiles = new HashSet<>();

        for (CandidatureFile candidatureFile : candidatureFiles) {
            CandidatureFile managedCandidature = candidatureFileRepository.findById(candidatureFile.getId()).orElse(null);
            managedCandidature.setCandidature(candidature);
            managedCnadidaturesFiles.add(managedCandidature);
        }

        candidature.setCandidatureFiles(managedCnadidaturesFiles);
        candidature.setDatepostlation(new Date());
        return candidatureRepository.save(candidature);


    }

    @Override
    public Candidature updateCandidature(Candidature candidature) {
        Candidature candidature1 = candidatureRepository.findById(candidature.getId()).orElse(null);
        candidature1.setMotivation(candidature.getMotivation());
        candidature1.setEtatcandidature(candidature.getEtatcandidature());
        candidature1.setCandidatureFiles(candidature.getCandidatureFiles());


        return candidatureRepository.save(candidature1);
    }

    @Override
    public void deleteCandidature(int idCandidature) {

    }

    @Override
    public List<Candidature> listCandidatureByOffre(int idOffre) {

        return candidatureRepository.findCandidaturesByOffre_Id(idOffre);
    }

    @Override
    public ResponseEntity<Resource> getFileCandidature(int idFile) {
        return null;
    }

    @Override
    public Candidature getCandidatureByUserAndOffre(int idUser, int idOffre) {
        return candidatureRepository.findCandidatureByIdUserAndOffreId(idUser,idOffre);
    }
}
