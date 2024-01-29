package com.example.quiz.Service;


import com.example.quiz.Entities.*;
import com.example.quiz.Repository.DomaineRepository;
import com.example.quiz.Repository.OptionRepository;
import com.example.quiz.Repository.QuestionRepository;
import com.example.quiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService {
    QuestionRepository questionRepository;
    UserRepository userRepository ;
    DomaineRepository domaineRepository ;
    OptionRepository optionRepository ;


    @Override
    @Transactional
    public Question addQuestionWithOptions(Question question , long idDomaine) {
        Domaine domaine =domaineRepository.findById(idDomaine).orElse(null) ;
        Set<Option> options = question.getOptions();
        Set<Option> managedOptions = new HashSet<>();
        for (Option option : options) {
            if (option.getOptionId() == null) {
                // Si l'option n'a pas d'ID (n'est pas persistée en base de données), enregistrez-la d'abord.
                option.setQuestion(question); // Assurez-vous que la question est définie pour l'option.
                option = optionRepository.save(option); // Enregistrez l'option et récupérez-la pour obtenir son ID généré.
            } else {
                // Si l'option a un ID, elle est déjà persistée, assurez-vous simplement que la question est définie.
                option.setQuestion(question);
            }
            managedOptions.add(option);
        }
        question.setOptions(managedOptions);
        question.setDomaine(domaine);
        return questionRepository.save(question);

    }


   /* private Option saveOption(Option option) {
        return optionRepository.save(option);
    }*/

    @Override
    public List<Question> getAllQuestion(long idDomaine) {

        return questionRepository.findAllByDomaine_DomaineId(idDomaine);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question updateQuestion(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public String deleteQuestion(long idQuestion) {
        if (questionRepository.findById(idQuestion).isPresent()){
            questionRepository.deleteById(idQuestion);
            return "question supprime" ;
        }else
            return "question non supprime " ;
    }

    @Override
    public Question getById(long idQuestion) {
        return questionRepository.findById(idQuestion).orElse(null);
    }
    @Override
    public Set<Option> getOptionsByQuestionId(Long questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            return question.get().getOptions();
        } else {
            throw new EntityNotFoundException("Question not found with ID: " + questionId);
        }
    }


        public int calculateDomainScore(Domaine domaine ,long idUser) {
            User user = userRepository.findById(idUser).orElse(null);

            if (user == null) {
                return 0; // L'utilisateur n'a pas été trouvé, renvoyer 0 ou gérer l'erreur comme nécessaire.
            }

            int totalScore = 0;

            for (Question question : domaine.getQuestions()) {
                int questionScore = question.getQuestionScore();
                int userScore = 0;

                for (Reponse reponse : question.getReponses()) {
                    if (reponse.getUserAnswer() != null && isUserAnswerCorrect(reponse.getUserAnswer(), question.getOptions())) {
                        userScore += questionScore;
                    }
                }

                totalScore += userScore;
            }

            user.setScore(totalScore);
            userRepository.save(user);
            return totalScore;
        }

    private boolean isUserAnswerCorrect(String userAnswer, Set<Option> options) {
        for (Option option : options) {
            if (option.getIsCorrect() && userAnswer.equals(option.getOptionText())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String NivauxOfUser(Long idUser) {
        User u = userRepository.findById(idUser).orElse(null);
        if(u.getScore()>=0&& u.getScore()<10){
            return "faible : il faut travailler " ;
        } else if (u.getScore()>9 && u.getScore()<15) {
            return "passable " ;
        } else if (u.getScore()>14 && u.getScore()<17) {
            return "bien" ;
        }else
            return "tres bien ";
    }
    public long countIncorrectReponsesInDomaine(Domaine domaine) {
        return questionRepository.countIncorrectReponsesInDomaine(domaine);
    }
}
