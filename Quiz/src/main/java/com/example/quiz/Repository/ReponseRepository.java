package com.example.quiz.Repository;


import com.example.quiz.Entities.Question;
import com.example.quiz.Entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse, Long> {
/*    @Query("SELECT r FROM Reponse r WHERE r.userAnswer <> ?1")
    List<Reponse> findFalseReponses(Boolean isCorrect);

    @Query("SELECT r FROM Reponse r WHERE r.userAnswer = :correctAnswer AND r.question IN :questions")
    List<Reponse> findTrueReponses(@Param("correctAnswer") String correctAnswer, @Param("questions") List<Question> questions);
    @Query("SELECT r FROM Reponse r WHERE r.userAnswer = 'false'")
    List<Reponse> findAllIncorrectReponses();*/

}
