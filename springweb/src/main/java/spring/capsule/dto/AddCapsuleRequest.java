package spring.capsule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.capsule.domain.Capsule;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCapsuleRequest {
    private String question;
    private String answer;
    private LocalDate qnadate;

    public Capsule toEntity() {
        return Capsule.builder()
                .question(question)
                .answer(answer)
                .qnadate(qnadate)
                .build();
    }
 
}
