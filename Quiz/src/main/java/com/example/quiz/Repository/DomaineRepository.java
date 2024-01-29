package com.example.quiz.Repository;

import com.example.quiz.Entities.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DomaineRepository extends JpaRepository<Domaine , Long> {
    @Query("SELECT SUM(CASE WHEN r.userAnswer != o.optionText THEN 1 ELSE 0 END) " +
            "FROM Reponse r " +
            "JOIN r.question q " +
            "JOIN q.domaine d " +
            "JOIN q.options o " +
            "WHERE d.domaineId = :domaineId")
    Integer countFalseReponsesInDomaine(@Param("domaineId") Long domaineId);

    @Query("SELECT COUNT(DISTINCT q) " +
            "FROM Question q " +
            "JOIN q.domaine d " +
            "JOIN q.reponses r " +
            "JOIN q.options o " +
            "WHERE d.domaineId = :domaineId " +
            "AND r.userAnswer != o.optionText")
    Long countQuestionsWithFalseReponsesInDomaine(@Param("domaineId") Long domaineId);

}
