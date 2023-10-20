package spring.capsule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.capsule.domain.Capsule;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCapsuleRequest {
    private String question;

    private String answer;

    public Capsule toEntity() {
        return Capsule.builder()
                .question(question)
                .answer(answer)
                .build();
    }
}
