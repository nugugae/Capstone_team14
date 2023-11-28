package spring.capsule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.capsule.domain.Emotion;
import spring.capsule.domain.User;
import spring.capsule.dto.AddEmotionRequest;
import spring.capsule.repository.EmotionRepository;
import spring.capsule.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class EmotionService {

    private final EmotionRepository emotionRepository;
    private final UserRepository userRepository;
    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }

    public Emotion save(AddEmotionRequest request, Long userId){
        User user = getUserById(userId);
        Emotion emotion= request.toEntity(user);
        return  emotionRepository.save(emotion);
    }


}
