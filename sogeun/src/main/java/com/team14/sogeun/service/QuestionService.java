package com.team14.sogeun.service;

import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }
}
