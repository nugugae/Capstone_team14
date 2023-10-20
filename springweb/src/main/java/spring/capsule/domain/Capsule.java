package spring.capsule.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "qna")
public class Capsule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qnaid", updatable = false)
    private Long qnaid;

    @Column(name = "question")//, nullable = false)
    private String question;

    @Column(name = "answer")//, nullable = false)
    private String answer;

    //이름 고민 원래 qnaDate
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //이거는 지워야할듯
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @Builder
    public Capsule(String question, String answer,LocalDateTime createdAt) {
        this.question = question;
        this.answer = answer;
        this.createdAt = createdAt;
    }

    public void update(String question, String answer,LocalDateTime createdAt) {
        this.question = question;
        this.answer = answer;
        this.createdAt = createdAt;
    }
}
