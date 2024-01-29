package com.example.serviceoffreemploi.Controllers;

import com.example.serviceoffreemploi.Config.KeycloakSecurityConfig;
import com.example.serviceoffreemploi.Entities.Candidature;
import com.example.serviceoffreemploi.Entities.CandidatureFile;
import com.example.serviceoffreemploi.Entities.Competence;
import com.example.serviceoffreemploi.Entities.Offre;
import com.example.serviceoffreemploi.Repositories.CandidatureFileRepository;
import com.example.serviceoffreemploi.Repositories.CandidatureRepository;
import com.example.serviceoffreemploi.Services.ICandidatureService;
import com.example.serviceoffreemploi.Services.ICompetenceService;
import com.example.serviceoffreemploi.Services.IOffreService;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;


@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RestController {

    @GetMapping("/hello")
    public String hello(){
        return "hi from service offre";
    }
   public IOffreService iOffreService;
    public ICompetenceService iCompetenceService;
    public ICandidatureService iCandidatureService;
   @GetMapping("/user/getAllOffres")
   public List<Offre> getAllOffres() {
       return iOffreService.getAllOffres();
   }



   @PostMapping("/admin/addOffre")
   public ResponseEntity<Offre>  addOfre(@RequestBody Offre offre , KeycloakAuthenticationToken auth) {

       KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
       KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
       boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
       if (hasUserRole){
           return iOffreService.addOfre(offre);
       }else {
           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       }

   }
    @PutMapping("/admin/updateOffre")
    @ResponseBody
    public Offre updateOffre(@RequestBody Offre offre) {
        return iOffreService.updateOffre(offre);
    }

    @GetMapping("/user/getOffreById/{idOffre}")
    public Offre getOffreById(@PathVariable("idOffre") int idOffre) {
        return iOffreService.getOffreById(idOffre);
    }

    @DeleteMapping("/admin/deleteOffre/{idOffre}")
    public void deleteOffre(@PathVariable("idOffre")int idOffre) {
       iOffreService.deleteOffre(idOffre);
    }



    @GetMapping("/admin/getAllCompetences")
    public List<Competence> getAllCompetences() {
        return iCompetenceService.getAllCompetence();
    }

    @PostMapping("/admin/addCompetence")
    public Competence addCompetence(@RequestBody Competence competence) {

        return iCompetenceService.addCompetence(competence);
    }


    @DeleteMapping("/admin/deleteCompetence/{id}")
    public void addCompetence(@PathVariable("id")int id) {

         iCompetenceService.deleteCompetence(id);
    }



    @PostMapping("/user/add")
    public Candidature addCandidature(@RequestParam("motivation") String motivation,
                                      @RequestParam("files") MultipartFile[] files,
                                      @RequestParam int idOffre,
                                      @RequestParam String idUser) {
       return iCandidatureService.addCandidature(motivation,files,idOffre,idUser);


    }

    @PutMapping("/admin/updateCandidature")
    public Candidature updateCandidature(@RequestBody Candidature candidature) {
        return iCandidatureService.updateCandidature(candidature);


    }


    @GetMapping("/user/getAllCandidature")
    public List<Candidature> getAllCandidature() {
        return iCandidatureService.getAllCandidature();
    }


    CandidatureFileRepository candidatureFileRepository;
    @GetMapping("/admin/getAllCandidatureFiles")
    public List<CandidatureFile> getAllCandidatureFiles() {

       return candidatureFileRepository.findAll();
    }


    @GetMapping("/admin/getAllCandidatureByOffre/{idOffre}")
    public List<Candidature> getAllCandidatureByOffre(@PathVariable("idOffre") int idOffre) {
        return iCandidatureService.listCandidatureByOffre(idOffre);
    }





    private final String uploadDir = "C:/Users/Kouki/Desktop/Esprit/5eme/Applications Web Distribuees/Project/spring/MicroService/serviceoffreemploi/src/uploads";

    @GetMapping("/admin/download-file/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);

        // Read the file content into a byte array
        byte[] fileContent = Files.readAllBytes(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }






    @GetMapping("/admin/download/{candidatureFileId}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable int candidatureFileId) {
        // Récupérez le CandidatureFile à partir de la base de données
        CandidatureFile candidatureFile = candidatureFileRepository.findById(candidatureFileId).orElse(null);

        if (candidatureFile == null) {
            // Gérez l'erreur si le fichier n'existe pas
            return ResponseEntity.notFound().build();
        }

        // Récupérez le chemin du fichier stocké
        Path filePath = Paths.get("C:/Users/Kouki/Desktop/Esprit/5eme/Applications Web Distribuees/Project/spring/MicroService/serviceoffreemploi/src/uploads")
                .toAbsolutePath().resolve(candidatureFile.getStoredFileName());

        FileSystemResource fileSystemResource = new FileSystemResource(filePath.toFile());

        if (fileSystemResource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + candidatureFile.getFileName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileSystemResource);
        } else {
            // Gérez l'erreur si le fichier n'existe pas sur le serveur
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/getCandidatureByUserAndOffre/{idUser}/{idOffre}")
    public Candidature getCandidatureByUserAndOffre(int idUser, int idOffre) {
            return iCandidatureService.getCandidatureByUserAndOffre(idUser,idOffre);
    }

}
