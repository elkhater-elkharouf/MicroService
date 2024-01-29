package com.example.quiz.Service;


import com.example.quiz.Entities.Option;
import com.example.quiz.Entities.Question;
import com.example.quiz.Repository.OptionRepository;
import com.example.quiz.Repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class OptionServiceImpl implements IOptionService{
    OptionRepository optionRepository ;
    QuestionRepository questionRepository ;


    @Override
    @Transactional
    public Option addOptionAndAsseignToQuestion(Option o, Long idQuestion) {
        Question question = questionRepository.findById(idQuestion)
                .orElse(null);



        if (question != null) {
            if (question.getOptions() == null) {
                question.setOptions(new HashSet<>());
            }

            question.getOptions().add(o);
            o.setQuestion(question);
            return optionRepository.save(o);
        } else {

            return null;
        }
}

    @Override
    public List<Option> GetAllOption() {
        return optionRepository.findAll();
    }

    @Override
    public Option updateOption(Option option) {
        return optionRepository.save(option) ;
    }

    @Override
    public String deleteOption(long idOption) {
        if (optionRepository.findById(idOption).isPresent()){
            optionRepository.deleteById(idOption);
            return "option supprime" ;
        }else
            return "option non supprime " ;
    }

    @Override
    public Option getById(long idOption) {
        return optionRepository.findById(idOption).orElse(null);
    }


}
