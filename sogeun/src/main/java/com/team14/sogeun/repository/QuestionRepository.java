package com.team14.sogeun.repository;
import com.team14.sogeun.domain.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
    List<Questions> findAll(String emotion);
}
