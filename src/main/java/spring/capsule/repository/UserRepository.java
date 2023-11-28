package spring.capsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.capsule.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //메일로 사용자 정보 가져옴
}
