package spring.capsule.dto;

import lombok.Getter;
import spring.capsule.domain.Capsule;

@Getter
public class CapsuleListViewResponse {
    private final Long qnaid;
    private final String question;
    private final String answer;

    public  CapsuleListViewResponse(Capsule capsule) {
        this.qnaid = capsule.getQnaid();
        this.question = capsule.getQuestion();
        this.answer = capsule.getAnswer();
    }
}
