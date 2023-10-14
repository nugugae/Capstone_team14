package spring.capsule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.capsule.domain.Capsule;
import spring.capsule.dto.AddCapsuleRequest;
import spring.capsule.dto.CapsuleResponse;
import spring.capsule.dto.UpdateCapsuleRequest;
import spring.capsule.service.CapsuleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CapsuleApiController {

    private final CapsuleService capsuleService;

    @PostMapping("/api/capsules")
    public ResponseEntity<Capsule> addCapsule(@RequestBody AddCapsuleRequest request) {
        Capsule savedCapsule = capsuleService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCapsule);
    }


    @GetMapping("/api/capsules")
    public ResponseEntity<List<CapsuleResponse>> findAllCapsules() {
        List<CapsuleResponse> capsules = capsuleService.findAll()
                .stream()
                .map(CapsuleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(capsules);
    }
    @GetMapping("/api/capsules/{id}")
    public ResponseEntity<CapsuleResponse> findCapsule(@PathVariable long id) {
        Capsule capsule = capsuleService.findById(id);

        return ResponseEntity.ok()
                .body(new CapsuleResponse(capsule));
    }
//
//    @DeleteMapping("/api/capsules/{id}")
//    public ResponseEntity<Void> deleteCapsule(@PathVariable long id) {
//        capsuleService.delete(id);
//
//        return ResponseEntity.ok()
//                .build();
//    }
//
//    @PutMapping("/api/capsules/{id}")
//    public ResponseEntity<Capsule> updateCapsule(@PathVariable long id,
//                                                 @RequestBody UpdateCapsuleRequest request) {
//        Capsule updatedCapsule = capsuleService.update(id, request);
//
//        return ResponseEntity.ok()
//                .body(updatedCapsule);
//    }

}

