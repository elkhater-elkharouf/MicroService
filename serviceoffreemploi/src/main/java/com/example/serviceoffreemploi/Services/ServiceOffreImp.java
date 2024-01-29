package com.example.serviceoffreemploi.Services;

import com.example.serviceoffreemploi.Entities.Competence;
import com.example.serviceoffreemploi.Entities.Offre;
import com.example.serviceoffreemploi.Repositories.CompetenceRepository;
import com.example.serviceoffreemploi.Repositories.OffreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ServiceOffreImp  implements IOffreService{

    public OffreRepository offreRepository;
    public CompetenceRepository competenceRepository;
    @Override
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    @Override
    public Offre getOffreById(int idOffre) {
        return offreRepository.findById(idOffre).orElse(null);
    }

    @Override
    public ResponseEntity<Offre> addOfre(Offre offre) {
        Set<Competence> competences =  offre.getCompetences();
        Set<Competence> managedCompetences = new HashSet<>();
        for (Competence competence : competences) {
            Competence managedCompetence = competenceRepository.findById(competence.getId()).orElse(null);
            managedCompetence.getOffres().add(offre);
            managedCompetences.add(managedCompetence);
        }
        offre.setCompetences(managedCompetences);
        offre.setDatepubilcation(new Date());
        offreRepository.save(offre);

        return new ResponseEntity<>(offreRepository.save(offre), HttpStatus.CREATED);
    }

    @Override
    public Offre updateOffre(Offre offre) {
        // Chargez l'offre existante depuis la base de données
        Offre offre1 = offreRepository.findById(offre.getId()).orElse(null);

        // Assurez-vous que l'offre existante est non nulle
        if (offre1 != null) {
            // Mise à jour des propriétés de l'offre existante avec les nouvelles valeurs
            offre1.setTitre(offre.getTitre());
            offre1.setDescription(offre.getDescription());
            offre1.setLocation(offre.getLocation());
            offre1.setStatut(offre.getStatut());
            offre1.setSalaire(offre.getSalaire());
            offre1.setTypecontrat(offre.getTypecontrat());
            // Mettez à jour d'autres propriétés ici


            offre1.setCompetences(offre.getCompetences());
            for (Competence competence : offre1.getCompetences()){
                System.out.println("competence :"+competence.getTitre());

            }
            // Enregistrez les modifications dans la base de données
            return offreRepository.save(offre1);
        } else {
            // Gérez le cas où l'offre n'existe pas
            return null;
        }
    }


    @Override
    public void deleteOffre(int idOffre) {

        Offre offre = offreRepository.findById(idOffre).orElse(null);

        if (offre != null) {
            // Dissocier les compétences de l'offre
            offre.getCompetences().forEach(competence -> competence.getOffres().remove(offre));
            offre.getCompetences().clear();

            // Supprimer l'offre
            offreRepository.delete(offre);
        }
    }
}
