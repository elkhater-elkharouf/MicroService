package com.example.serviceoffreemploi.Services;

import com.example.serviceoffreemploi.Entities.Competence;
import com.example.serviceoffreemploi.Repositories.CompetenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceCompetenceImp implements ICompetenceService{
private CompetenceRepository competenceRepository;
    @Override
    public List<Competence> getAllCompetence() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence getCompetenceById(int idCompetence) {
        return competenceRepository.findById(idCompetence).orElse(null);
    }

    @Override
    public Competence addCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public Competence updateCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public void deleteCompetence(int idCompetence) {
        competenceRepository.deleteById(idCompetence);
    }
}
