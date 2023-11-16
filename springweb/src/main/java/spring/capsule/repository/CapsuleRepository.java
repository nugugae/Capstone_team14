package spring.capsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.capsule.domain.Capsule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CapsuleRepository extends JpaRepository<Capsule, Long> {

    List<Capsule> findAllByQnadate(LocalDate date);
    @Query("SELECT c FROM Capsule c WHERE c.user.uid = :userId")
    List<Capsule> findAllByUserId(@Param("userId") Long userId);

    // JPA Query to find capsules by user ID and date
    @Query("SELECT c FROM Capsule c WHERE c.user.uid = :userId AND c.qnadate = :date")
    List<Capsule> findAllByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);

}
