package spring.capsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.capsule.domain.Capsule;

public interface CapsuleRepository extends JpaRepository<Capsule, Long> {
}

