package com.example.quiz.Service;


import com.example.quiz.Entities.Option;

import java.util.List;

public interface IOptionService {
    public Option addOptionAndAsseignToQuestion(Option o, Long idQuestion) ;
    public List<Option> GetAllOption() ;
    public Option updateOption(Option option) ;
    public String deleteOption(long idOption) ;
    public Option getById(long idOption);



}
