package spring.capsule.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.capsule.domain.Capsule;
import spring.capsule.dto.AddCapsuleRequest;
import spring.capsule.dto.CapsuleViewResponse;
import spring.capsule.dto.UpdateCapsuleRequest;
import spring.capsule.repository.CapsuleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CapsuleService {

    private final CapsuleRepository capsuleRepository;

    //캡슐추가 메서드
    public Capsule save(AddCapsuleRequest request) {
        return capsuleRepository.save(request.toEntity());
    }

    public List<Capsule> findAll() {
        return capsuleRepository.findAll();
    }

    public Capsule findById(long id) {
        return capsuleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }


    public List<Capsule> findByDate(LocalDate date) {
        return capsuleRepository.findByQnadate(date);
    }

    public Capsule saveWithDate(AddCapsuleRequest request, LocalDate date) {
        Capsule capsule = request.toEntity();
        capsule.setQnadate(date);
        return capsuleRepository.save(capsule);
    }
    public Map<LocalDate, List<CapsuleViewResponse>> findAllGroupedByDate() {
        List<Capsule> allCapsules = capsuleRepository.findAll();
        return allCapsules.stream()
                .map(CapsuleViewResponse::new)
                .collect(Collectors.groupingBy(CapsuleViewResponse::getQnadate));

    }

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
