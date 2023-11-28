package spring.capsule.dto;

import lombok.Getter;
import spring.capsule.domain.Emotion;

@Getter
public class EmotionResponse {

    private final String mood;

    public EmotionResponse(Emotion emotion) {
        this.mood = emotion.getMood();
    }
}
