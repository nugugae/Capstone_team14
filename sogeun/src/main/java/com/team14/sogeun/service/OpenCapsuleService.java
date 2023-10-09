package com.team14.sogeun.service;

import com.team14.sogeun.domain.entity.Answers;
import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.repository.AnswerRepository;
import com.team14.sogeun.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenCapsuleService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public Map<Questions, Answers> getQnAOlderThanAWeek(Long userId) {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        List<Questions> questions = questionRepository.findByOpenCapsule(sevenDaysAgo, userId);
        List<Answers> answers = answerRepository.findByQuestionIn(questions);

        Map<Questions, Answers> qnaMap = new HashMap<>();
        for (Questions question : questions) {
            for (Answers answer : answers) {
                if (answer.getQuestion().equals(question)) {
                    qnaMap.put(question, answer);
                }
            }
        }
        return qnaMap;
    }
}
