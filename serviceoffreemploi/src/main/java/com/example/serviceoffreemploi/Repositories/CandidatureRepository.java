package com.example.serviceoffreemploi.Repositories;

import com.example.serviceoffreemploi.Entities.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

    public List<Candidature> findCandidaturesByOffre_Id(int idOffre);

    public Candidature findCandidatureByIdUserAndOffreId(int idUser , int idOffre);
}