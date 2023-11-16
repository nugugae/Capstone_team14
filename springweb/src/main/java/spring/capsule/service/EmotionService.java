package spring.capsule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.Emotion;
import spring.capsule.domain.User;
import spring.capsule.dto.AddEmotionRequest;
import spring.capsule.dto.CapsuleViewResponse;
import spring.capsule.dto.EmotionViewResponse;
import spring.capsule.repository.EmotionRepository;
import spring.capsule.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Emotion> findAllByUserIdAndDate(Long userId, LocalDate date) {
        // Use the repository to fetch capsules by user ID and date
        return emotionRepository.findAllByUserIdAndDate(userId, date);
    }

    public Map<LocalDate, List<EmotionViewResponse>> findAllByUserIdGroupedByDate(Long userId) {


        List<Emotion> emotions = emotionRepository.findAllByUserId(userId);

        // Group   by date
        return emotions.stream()
                .collect(Collectors.groupingBy(
                        Emotion::getMdate,
                        Collectors.mapping(EmotionViewResponse::new, Collectors.toList())
                ));
    }


}
