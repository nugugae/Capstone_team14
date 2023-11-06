package spring.capsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.capsule.domain.Capsule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CapsuleRepository extends JpaRepository<Capsule, Long> {

    // Change the method signature to return an Optional<Capsule>
    List<Capsule> findAllByQnadate(LocalDate date);
    //Optional<Capsule> findByQnadate(LocalDate qnadate);
}
