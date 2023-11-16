package spring.capsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.capsule.domain.Emotion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    List<Emotion> findAllByMoodDate(LocalDate date);

    @Query("SELECT e FROM Emotion e WHERE e.user.uid = :userId")
    List<Emotion> findAllByUserId(@Param("userId") Long userId);

    // JPA Query to find capsules by user ID and date
    @Query("SELECT e FROM Emotion e WHERE e.user.uid = :userId AND e.moodDate = :date")
    List<Emotion> findAllByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);

}
