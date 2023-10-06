package com.team14.sogeun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team14.sogeun.domain.entity.Answers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Long> {

    @Query("SELECT a FROM Answers a WHERE a.question.user.id = :userId AND FUNCTION('DATE', a.answerDateTime) = :answerDate")
    List<Answers> findByUserIdAndAnswerDate(Long userId, LocalDate answerDate);
}