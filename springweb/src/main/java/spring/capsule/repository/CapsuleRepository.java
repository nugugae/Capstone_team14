package spring.capsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.capsule.domain.Capsule;

import java.time.LocalDate;
import java.util.List;

public interface CapsuleRepository extends JpaRepository<Capsule, Long> {
    List<Capsule> findByQnadate(LocalDate qnadate);
    //@Query("SELECT c FROM Capsule c WHERE DATE(c.qnadate) = :date")
    //List<Capsule> findByQnadate(@Param("date") LocalDate date);

}

