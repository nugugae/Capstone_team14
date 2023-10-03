package com.team14.sogeun.service;

import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Questions saveQuestion(Questions question) {
        return questionRepository.save(question);
    }

    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Questions> getYesterdayQuestions(Long userId) {
        LocalDateTime now = LocalDateTime.now();//현재
        LocalDateTime startDate = now.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);//어제 원하면 7롷 변경해도 될 듯
        LocalDateTime endDate = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        return questionRepository.findByUserIdAndQuestionDateBetween(userId, startDate, endDate);
    }
    public Questions saveEmotionForToday(Questions question) {
        // today의 사용자 감정 상태가 이미 저장되었는지 확인
        Questions existingQuestion = questionRepository.findByUserIdAndQuestionDate(question.getUser().getId(), LocalDate.now());
        if(existingQuestion != null) {
            throw new IllegalArgumentException("Emotion for today is already recorded.");
        }
        return questionRepository.save(question);
    }
}
