package com.team14.sogeun.repository;
import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
    @Query("SELECT q FROM Questions q WHERE q.user.id = :userId AND q.questionDate BETWEEN :startDate AND :endDate")
    List<Questions> findByUserIdAndQuestionDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    // 1일마다 사용자의 감정을 조회
    Questions findByUserIdAndQuestionDate(Long userId, LocalDate date);

}
