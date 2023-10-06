package com.team14.sogeun.repository;
/*

import com.team14.sogeun.domain.entity.Answers;
import com.team14.sogeun.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DataJpaTest
class AnswerRepositoryTest {
    @Autowired
    private AnswerRepository answersRepository;

    @Test
    public void testFindByUserIdAndAnswerDate() {
        // 여기에 테스트 데이터를 삽입
        // 예를 들면, Answers 객체를 생성하고 저장한 후
        // findByUserIdAndAnswerDate 메서드를 호출하여 결과를 검증

        User user = new User();
        // 예시:
        Answers answer = new Answers();
        // 필요한 필드 설정
        answersRepository.save(answer);

        List<Answers> results = answersRepository.findByUserIdAndAnswerDate(
                user.getId(), LocalDate.now());

        assertThat(results).isNotEmpty();
    }

}*/