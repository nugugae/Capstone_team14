package spring.capsule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.capsule.domain.Capsule;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class CapsuleViewResponse {
    private Long qnaid;
    private String question;
    private String answer;
    private LocalDate qnadate;

    public CapsuleViewResponse(Capsule capsule) {
        this.qnaid = capsule.getQnaid();
        this.question = capsule.getQuestion();
        this.answer = capsule.getAnswer();
        this.qnadate = capsule.getQnadate();
    }
}
