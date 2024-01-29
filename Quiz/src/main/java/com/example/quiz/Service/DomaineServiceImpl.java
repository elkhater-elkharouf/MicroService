package com.example.quiz.Service;

import com.example.quiz.Entities.Domaine;
import com.example.quiz.Repository.DomaineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DomaineServiceImpl implements IDomaineService{
DomaineRepository domaineRepository ;
    @Override
    public Domaine addDomaine(Domaine domaine) {
        return domaineRepository.save(domaine);
    }

    @Override
    public Domaine getDomaineById(long idDomaine) {
        return domaineRepository.findById(idDomaine).orElse(null);
    }

    @Override
    public Domaine updateDomaine(Domaine domaine) {
        return domaineRepository.save(domaine);
    }

    @Override
    public String deleteDomaine(long idDomaine) {
        if (domaineRepository.findById(idDomaine).isPresent()){
            domaineRepository.deleteById(idDomaine);
            return "domaine  supprime" ;
        }else
            return "domaine non supprime " ;
    }

    @Override
    public List<Domaine> getAllDomaine() {
        return domaineRepository.findAll();
    }
}
