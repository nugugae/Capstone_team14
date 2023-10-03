package com.team14.sogeun.repository;

import com.team14.sogeun.domain.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Long> {

    List<Answers> findByUserIdAndAnswerDate(Long userId, LocalDate answerDate);
    List<Answers> findAll(String emotion);
}
