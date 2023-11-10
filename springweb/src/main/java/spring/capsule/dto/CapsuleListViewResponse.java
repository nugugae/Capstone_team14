package spring.capsule.dto;

import lombok.Getter;
import spring.capsule.domain.Capsule;

import java.time.LocalDate;

@Getter
public class CapsuleListViewResponse {
    private final Long qnaid;
    private final String question;
    private final String answer;
    private final LocalDate qnadate;

    public  CapsuleListViewResponse(Capsule capsule) {
        this.qnaid = capsule.getQnaid();
        this.question = capsule.getQuestion();
        this.answer = capsule.getAnswer();
        this.qnadate = capsule.getQnadate();
    }
}
