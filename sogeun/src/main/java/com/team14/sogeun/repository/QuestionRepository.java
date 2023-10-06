package com.team14.sogeun.repository;

import com.team14.sogeun.domain.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {


    //@Query("SELECT q FROM Questions q WHERE q.user.id = :userId AND q.questionDate BETWEEN :startDate AND :endDate")

    //시작 날짜(startDate)와 종료 날짜(endDate) 사이에 생성된 Questions 엔티티의 목록을 반환
    List<Questions> findByUserIdAndQuestionDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    // 주어진 날짜(questionDate)에 생성된 엔티티 반환-> 사용자가 해당 날짜 작성한 질문을 보기 위해 존재
    Questions findByUserIdAndQuestionDate(Long userId, LocalDateTime questionDate);
}
