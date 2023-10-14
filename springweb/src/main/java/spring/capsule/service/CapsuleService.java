package spring.capsule.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.capsule.domain.Capsule;
import spring.capsule.dto.AddCapsuleRequest;
import spring.capsule.dto.UpdateCapsuleRequest;
import spring.capsule.repository.CapsuleRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CapsuleService {

    private final CapsuleRepository capsuleRepository;

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
