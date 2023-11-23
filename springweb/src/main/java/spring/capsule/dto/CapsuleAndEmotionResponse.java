package spring.capsule.dto;

import lombok.Getter;
import lombok.Setter;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.Emotion;
@Setter
@Getter
public class CapsuleAndEmotionResponse {
    private Capsule capsule;
    private Emotion emotion;
}
