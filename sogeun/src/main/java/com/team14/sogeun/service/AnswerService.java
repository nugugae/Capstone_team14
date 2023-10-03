package com.team14.sogeun.service;

import com.team14.sogeun.domain.entity.Answers;
import com.team14.sogeun.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class AnswerService {
    @Autowired
    private AnswerRepository answersRepository;

    public List<Answers> getAnswersByUserId(Long userId) {
        return answersRepository.findByUserIdAndAnswerDate(userId, LocalDate.now());
    }

    public Answers saveAnswer(Answers answer) {
        return answersRepository.save(answer);
    }

    public List<Answers> getAnswersByEmotion(String emotion) {
        return answersRepository.findByEmotion(emotion);
    }

}
