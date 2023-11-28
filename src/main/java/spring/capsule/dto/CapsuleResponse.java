package spring.capsule.dto;

import lombok.Getter;
import spring.capsule.domain.Capsule;

@Getter
public class CapsuleResponse {

    private final String question;
    private final String answer;

    public CapsuleResponse(Capsule capsule) {
        this.question = capsule.getQuestion();
        this.answer = capsule.getAnswer();
    }
}
