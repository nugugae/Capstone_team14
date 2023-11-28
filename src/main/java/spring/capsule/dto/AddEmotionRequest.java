package spring.capsule.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.capsule.domain.Emotion;
import spring.capsule.domain.User;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddEmotionRequest {
    private String mood;
    private LocalDate moodDate;
    public Emotion toEntity(User user){
        return Emotion.builder()
                .user(user)
                .mood(mood)
                .moodDate(moodDate)
                .build();
    }
}
