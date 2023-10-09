package com.team14.sogeun.repository;

import com.team14.sogeun.domain.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import com.team14.sogeun.domain.entity.Answers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Long> {
    @Query("SELECT a FROM Answers a WHERE a.question.user.id = :userId AND FUNCTION('DATE', a.answerDate) = :answerDate")
    List<Answers> findByUserIdAndAnswerDate(Long userId, LocalDate answerDate);

    //지난 날
    List<Answers> findByQuestionIn(List<Questions> questions);

    Optional<Answers> findByQuestionId(Long questionId);//all day


    }