package com.example.quiz.Service;


import com.example.quiz.Entities.Option;
import com.example.quiz.Entities.Question;
import com.example.quiz.Entities.Reponse;
import com.example.quiz.Entities.User;
import com.example.quiz.Repository.OptionRepository;
import com.example.quiz.Repository.ReponseRepository;
import com.example.quiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    ReponseRepository reponseRepository ;
    OptionRepository optionRepository ;
    UserRepository userRepository ;




  /*  @Override
    @Transactional

    public void calculateAndUpdateUserScore(User user, Question question, String userAnswer) {
        int scoreIncrement = 0;

        for (Option option : question.getOptions()) {
            if (userAnswer != null && userAnswer.equals(option.getOptionText()) && option.getIsCorrect()) {
                scoreIncrement += question.getQuestionScore();
            }
        }

        // Mettez à jour le score de l'utilisateur
        user.setScore(user.getScore() + scoreIncrement);
        userRepository.save(user);
    }*/
    }

    /*    @Override
        public User calculateScore(Long idUser) {
            int score = 0;

            // Récupérez toutes les réponses de l'utilisateur
            List<SecurityAnswer> userAnswers = securityAnswerRepository.getByUserId(idUser);

            for (SecurityAnswer userAnswer : userAnswers) {
                SecurityQuestion question = userAnswer.getSecurityQuestion();
                Option correctOption = optionRepository.findByQuestionAndIsCorrect(question, true);

                if (correctOption != null && userAnswer.getUserAnswer() != null && userAnswer.getUserAnswer().equalsIgnoreCase(correctOption.getOptionText())) {
                    score++; // Incrémente le score si la réponse de l'utilisateur est correcte
                }
            }

            return new ScoreDTO(userId, score);
        }*/


