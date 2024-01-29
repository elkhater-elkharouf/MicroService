package com.example.quiz.Service;
import com.example.quiz.Entities.*;
import com.example.quiz.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReponseService implements IReponseService {
ReponseRepository reponseRepository ;
QuestionRepository questionRepository ;
OptionRepository optionRepository ;
UserRepository userRepository ;
DomaineRepository domaineRepository ;
    @Override
    @Transactional

    public Reponse addAnswer(Reponse reponse , long idQuestion , long idUser) {

        Question question = questionRepository.findById(idQuestion).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);


        // Vérifier que la question et l'utilisateur existent
        if (question != null && user != null) {
            // Associer la réponse à la question
            reponse.setQuestion(question);

            // Créer une nouvelle liste d'utilisateurs si elle est nulle
            if (reponse.getUsers() == null) {
                reponse.setUsers(new HashSet<>());
            }

            // Ajouter l'utilisateur à la liste des utilisateurs associés à la réponse
            reponse.getUsers().add(user);

            // Enregistrer la réponse
            return reponseRepository.save(reponse);
        } else {
            // Gérer les cas où la question ou l'utilisateur n'a pas été trouvé
            return null;
        }
    }


    public Reponse addAnswer(Reponse reponse , long idQuestion , long idUser, List<Option> optionsChoisies) {

        Question question = questionRepository.findById(idQuestion).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);


        // Vérifier que la question et l'utilisateur existent
        if (question != null && user != null) {
            // Associer la réponse à la question
            reponse.setQuestion(question);

            // Créer une nouvelle liste d'utilisateurs si elle est nulle
            if (reponse.getUsers() == null) {
                reponse.setUsers(new HashSet<>());
            }

            // Ajouter l'utilisateur à la liste des utilisateurs associés à la réponse
            reponse.getUsers().add(user);
           // reponse.setOptions(new HashSet<>(optionsChoisies));
            // Enregistrer la réponse
            return reponseRepository.save(reponse);
        } else {
            // Gérer les cas où la question ou l'utilisateur n'a pas été trouvé
            return null;
        }
    }
    @Override
    public List<Reponse> getAllReponse() {
        return reponseRepository.findAll();
    }

    @Override
    public Reponse updateAnswer(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public String deleteResponse(long idResponse) {
        if (reponseRepository.findById(idResponse).isPresent()){
            reponseRepository.deleteById(idResponse);
            return "reponse  supprime" ;
        }else
            return "reponse non supprime " ;
    }

    @Override
    public Reponse getById(long idReponse) {
        return reponseRepository.findById(idReponse).orElse(null);
    }


/*    @Override
    public int countFalseReponsesInDomaine(Long domaineId) {
        Domaine domaine = domaineRepository.findById(domaineId).orElse(null);
        if (domaine == null) {
            throw new DomaineNotFoundException("Domaine not found with id: " + domaineId);
        }

        int count = 0;
        for (Question question : domaine.getQuestions()) {
            for (Reponse reponse : question.getReponses()) {
                if (!reponse.isUserAnswerCorrect()) {
                    count++;
                    break; // Exit the inner loop if a wrong answer is found
                }
            }
        }

        return count;
    }*/
    }



