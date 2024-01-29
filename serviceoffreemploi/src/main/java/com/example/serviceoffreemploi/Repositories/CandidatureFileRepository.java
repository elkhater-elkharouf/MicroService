package com.example.serviceoffreemploi.Repositories;

import com.example.serviceoffreemploi.Entities.CandidatureFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureFileRepository extends JpaRepository<CandidatureFile, Integer> {
}