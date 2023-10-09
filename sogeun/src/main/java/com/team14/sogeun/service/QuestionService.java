package com.team14.sogeun.service;

import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.domain.entity.User;
import com.team14.sogeun.repository.QuestionRepository;
import com.team14.sogeun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public List<Questions> getQuestionsByUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        return userOpt.map(User::getQuestions).orElseGet(ArrayList::new);
    }

    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }


    public List<Questions> getUserQuestionsLastWeek(Long userId) {
        LocalDate endDate = LocalDate.now(); // 오늘 날짜
        LocalDate startDate = endDate.minusDays(7); // 일주일 전 ->원하면 하루전으로 해도 ㄱㅊ (1)로 변경

        return questionRepository.findByUserIdAndQuestionDateBetween(userId, startDate, endDate);
    }
    public Questions saveQuestion(String questiontxt) {
        Questions question = new Questions();
        question.setQuestion(questiontxt);
        question.setQuestionDate(LocalDateTime.now());
        return questionRepository.save(question);
    }

    public List<String> getUserEmotionsLastWeek(Long userId) {
        LocalDate endDate = LocalDate.now(); // 오늘 날짜
        LocalDate startDate = endDate.minusDays(7);// 일주일 전
        return questionRepository.findEmotions(userId, startDate, endDate);
    }
    public List<String> getUserEmotionsLastMonth(Long userId) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);//한달 전
        return questionRepository.findEmotions(userId, startDate, endDate);
    }


/*

    public Questions getLatestQuestion() {
        // GPT로부터의 질문
        String gptQuestion = "prompt...";

        Questions question = new Questions();
        question.setQuestion(gptQuestion);
        question.setQuestionDate(LocalDateTime.now()); // 현재 시간 설정

        return questionRepository.save(question); // 질문 저장 및 반환
    }*/
}
