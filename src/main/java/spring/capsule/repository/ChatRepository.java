package spring.capsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.capsule.domain.Message;

@Repository
public interface ChatRepository extends JpaRepository<Message, Long> {
}

