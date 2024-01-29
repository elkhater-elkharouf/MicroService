package com.example.serviceoffreemploi.Repositories;

import com.example.serviceoffreemploi.Entities.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
}