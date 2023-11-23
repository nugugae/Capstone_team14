package spring.capsule.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.Emotion;
import spring.capsule.domain.User;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCapsuleRequest {
    private String question;
    private String answer;
    private LocalDate qnadate;
    private String mood;
    private LocalDate mdate;
    public Emotion toEmotionEntity(User user){
        return Emotion.builder()
                .user(user)
                .mood(mood)
                .mdate(mdate)
                .build();
    }
    public Capsule toEntity(User user) {
        return Capsule.builder()
                .user(user)
                .question(question)
                .answer(answer)
                .qnadate(qnadate)
                .build();
    }
 
}
