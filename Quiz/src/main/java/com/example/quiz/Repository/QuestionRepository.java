package com.example.quiz.Repository;

import com.example.quiz.Entities.Domaine;
import com.example.quiz.Entities.Option;
import com.example.quiz.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository  extends JpaRepository<Question, Long> {
    @Query("SELECT COUNT(q) FROM Question q " +
            "JOIN q.reponses r " +
            "JOIN q.options o " +
            "WHERE q.domaine = :domaine " +
            "AND r.userAnswer != o.optionText")
    long countIncorrectReponsesInDomaine(@Param("domaine") Domaine domaine);

    List<Question> findAllByDomaine_DomaineId(long domaineId) ;
}
