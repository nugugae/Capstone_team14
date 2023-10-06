package com.team14.sogeun.service;

import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    public Questions saveQuestion(Questions question) {
        return questionRepository.save(question);
    }
    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }
    public List<Questions> getUserQuestionsForLastWeek(Long userId) {
        LocalDate endDate = LocalDate.now(); // 오늘 날짜
        LocalDate startDate = endDate.minusDays(7); // 일주일 전 ->원하면 하루전으로 해도 ㄱㅊ (1)로 변경

        return questionRepository.findByUserIdAndQuestionDateBetween(userId, startDate, endDate);
    }
}
