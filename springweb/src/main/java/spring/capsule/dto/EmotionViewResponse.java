package spring.capsule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.capsule.domain.Emotion;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class EmotionViewResponse {
    private Long mid;
    private String mood;
    private LocalDate mdate;

    public EmotionViewResponse(Emotion emotion) {
        this.mid =emotion.getMid();
        this.mood = emotion.getMood();
        this.mdate =emotion.getMdate();
    }

}

