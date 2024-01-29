package com.example.serviceoffreemploi.Repositories;

import com.example.serviceoffreemploi.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
}