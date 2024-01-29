package com.example.quiz.Service;

import com.example.quiz.Entities.Domaine;

import java.util.List;

public interface IDomaineService {
    public Domaine addDomaine(Domaine domaine) ;
    public Domaine getDomaineById(long idDomaine);
    public Domaine updateDomaine(Domaine domaine) ;
    public String deleteDomaine(long idDomaine) ;
    public List<Domaine> getAllDomaine() ;
}
