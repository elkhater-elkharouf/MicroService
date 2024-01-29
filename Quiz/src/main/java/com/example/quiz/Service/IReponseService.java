package com.example.quiz.Service;


import com.example.quiz.Entities.Option;
import com.example.quiz.Entities.Question;
import com.example.quiz.Entities.Reponse;

import java.util.List;

public interface IReponseService {
    public Reponse addAnswer(Reponse reponse , long idQuestion , long idUser) ;
    public List<Reponse> getAllReponse() ;
    public Reponse updateAnswer(Reponse reponse) ;
    public String deleteResponse(long idResponse) ;
    public Reponse getById(long idReponse) ;
    //public Long countFalseReponsesInDomaine(Long domaineId) ;


}
