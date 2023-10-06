package com.team14.sogeun.service;
import com.team14.sogeun.repository.QuestionRepository;
import com.team14.sogeun.repository.AnswerRepository;
import com.team14.sogeun.domain.entity.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DailyService {
    @Autowired
    private QuestionRepository questionsRepository;
    @Autowired
    private AnswerRepository answersRepository;

    @Scheduled(cron = "0 0 0 * * ?")// 매일 자정 (0 시 0 분 0 초)에 실행되도록 스케줄링된 작업
    public void saveDailyData() {
        // 매일 데이터 저장 로직
    }
/* w전날 데이터
    public Answers getYesterdayAnswer(Long userId) {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return answersRepository.findByUserIdAndAnswerDate(userId, yesterday);
    }

    public String generateGptPrompt(Long userId) {
        Answers yesterdayAnswer = getYesterdayAnswer(userId);
        String prompt = "Based on yesterday's diary: " + yesterdayAnswer.getAnswer();
        return prompt;
    }

 */
}
