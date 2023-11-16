package spring.capsule.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.User;
import spring.capsule.dto.AddCapsuleRequest;
import spring.capsule.dto.CapsuleViewResponse;
import spring.capsule.dto.UpdateCapsuleRequest;
import spring.capsule.repository.CapsuleRepository;
import spring.capsule.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CapsuleService {

    private final CapsuleRepository capsuleRepository;
    private final UserRepository userRepository;
    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }
    public List<Capsule> getChatHistoryByDateAndUser(Long userId, LocalDate date) {
        return capsuleRepository.findAllByUserIdAndDate(userId, date);
    }
    //캡슐추가 메서드
    public Capsule save(AddCapsuleRequest request, Long userId) {
        User user = getUserById(userId);

        // Create a Capsule entity using the request and the fetched user
        Capsule capsule = request.toEntity(user);

        // Save the capsule entity to the database
        return capsuleRepository.save(capsule);
    }

    public List<Capsule> findAll() {
        return capsuleRepository.findAll();
    }

    public Capsule findById(long id) {
        return capsuleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }
//    public Optional<Capsule> findByDate(LocalDate date) {
//        return capsuleRepository.findByQnadate(date);
//    }
    public List<Capsule> findAllByDate(LocalDate date) {
        return capsuleRepository.findAllByQnadate(date);
    }


    public Map<LocalDate, List<CapsuleViewResponse>> findAllByUserIdGroupedByDate(Long userId) {
        // Use the repository to fetch capsules by user ID
        List<Capsule> capsules = capsuleRepository.findAllByUserId(userId);

        // Group capsules by date
        return capsules.stream()
                .collect(Collectors.groupingBy(
                        Capsule::getQnadate,
                        Collectors.mapping(CapsuleViewResponse::new, Collectors.toList())
                ));
    }

    // Implement this method to find capsules by user ID and date
    public List<Capsule> findAllByUserIdAndDate(Long userId, LocalDate date) {
        // Use the repository to fetch capsules by user ID and date
        return capsuleRepository.findAllByUserIdAndDate(userId, date);
    }

    public Capsule saveWithDate(AddCapsuleRequest request, LocalDate date, Long userId) {
        User user = getUserById(userId);
        Capsule capsule = request.toEntity(user);
        capsule.setQnadate(date);
        return capsuleRepository.save(capsule);
    }
    public Map<LocalDate, List<CapsuleViewResponse>> findAllGroupedByDate() {
        List<Capsule> allCapsules = capsuleRepository.findAll();
        return allCapsules.stream()
                .map(CapsuleViewResponse::new)
                .collect(Collectors.groupingBy(CapsuleViewResponse::getQnadate));

    }
    @Transactional
    public List<Capsule> saveOrUpdateTodayCapsule(AddCapsuleRequest request) {
        LocalDate today = LocalDate.now();
        List<Capsule> capsules = capsuleRepository.findAllByQnadate(today);

        if (capsules.isEmpty()) {
            // If no capsules are found for today, create a new one
            Capsule newCapsule = new Capsule(today);
            newCapsule.addQuestion(request.getQuestion());
            newCapsule.addAnswer(request.getAnswer());
            // Save the new capsule
            capsuleRepository.save(newCapsule);
            // Add the new capsule to the list
            capsules.add(newCapsule);
        } else {
            // If capsules are found, update them with the new question and answer
            for (Capsule capsule : capsules) {
                capsule.addQuestion(request.getQuestion());
                capsule.addAnswer(request.getAnswer());
                // Update the capsule
                capsuleRepository.save(capsule);
            }
        }

        return capsules;
    }

//    @Transactional
//    public Capsule saveOrUpdateTodayCapsule(AddCapsuleRequest request) {
//        LocalDate today = LocalDate.now();
//        Capsule capsule = capsuleRepository.findAllByQnadate(today)
//                .orElseGet(() -> new Capsule(today)); // Assuming there's a constructor in Capsule class that takes the date
//
//        // Assuming there are methods to add questions and answers to the capsule
//        capsule.addQuestion(request.getQuestion());
//        capsule.addAnswer(request.getAnswer());
//
//        return capsuleRepository.save(capsule);
//    }


}
//    public void delete(long id) {
//        capsuleRepository.deleteById(id);
//    }
//
//    @Transactional
//    public Capsule update(long id, UpdateCapsuleRequest request) {
//        Capsule capsule = capsuleRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
//
//        capsule.update(request.getTitle(), request.getContent());
//
//        return capsule;
//    }
//}
