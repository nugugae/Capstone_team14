package com.team14.sogeun.service;


import com.team14.sogeun.domain.entity.Answers;
import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;


    public Answers saveAnswer(String answertxt) { //답문 저장
        Answers answer = new Answers();
        answer.setAnswer(answertxt);
        answer.setAnswerDate(LocalDateTime.now());
        return answerRepository.save(answer);
    }
    //all day
    public Answers getAnswerByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId).orElse(null);
    }

}
