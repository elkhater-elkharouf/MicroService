package com.example.quiz.Service;


import com.example.quiz.Entities.Option;
import com.example.quiz.Entities.Question;

import java.util.List;
import java.util.Set;

public interface IQuestionService {
    public Question addQuestionWithOptions(Question question, long idDomaine);
    public List<Question> getAllQuestion(long idDomaine) ;
    public List<Question> getAllQuestions();
    public Question updateQuestion(Question q) ;
    public String deleteQuestion(long idQuestion) ;
    public Question getById(long idQuestion);
    public Set<Option> getOptionsByQuestionId(Long questionId);
    public String NivauxOfUser(Long idUser) ;

}
