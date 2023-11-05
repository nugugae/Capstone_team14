package spring.capsule.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.User;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCapsuleRequest {
    //@NotNull
    private String question;
    //@NotNull
    private String answer;
    private LocalDate qnadate;

    public Capsule toEntity(User user) {
        return Capsule.builder()
                .user(user)
                .question(question)
                .answer(answer)
                .qnadate(qnadate)
                .build();
    }
 
}
