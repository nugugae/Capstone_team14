package spring.capsule.dto;

import lombok.Getter;
import spring.capsule.domain.Capsule;

import java.time.LocalDateTime;

@Getter
public class CapsuleListViewResponse {
    private final Long qnaid;
    private final String question;
    private final String answer;
    private final LocalDateTime createdAt;

    public  CapsuleListViewResponse(Capsule capsule) {
        this.qnaid = capsule.getQnaid();
        this.question = capsule.getQuestion();
        this.answer = capsule.getAnswer();
        this.createdAt = capsule.getCreatedAt();
    }
}
