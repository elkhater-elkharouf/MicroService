package com.example.serviceoffreemploi.Services;

import com.example.serviceoffreemploi.Entities.Competence;
import com.example.serviceoffreemploi.Entities.Offre;

import java.util.List;

public interface ICompetenceService {
    public List<Competence> getAllCompetence();
    public Competence getCompetenceById(int idCompetence);
    public Competence addCompetence(Competence competence);
    public Competence updateCompetence(Competence competence);
    public void deleteCompetence(int idCompetence);
}
