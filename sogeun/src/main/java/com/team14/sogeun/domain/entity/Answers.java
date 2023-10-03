package com.team14.sogeun.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "answer_Id")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_Id")
    private Questions question;

    @Column(name = "answer_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime answerDateTime;

    @Column(name = "answer", columnDefinition = "TEXT", nullable = false)
    private String answer;

    @Builder.Default  // 추가된 부분
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answers> answers = new ArrayList<>();

    public void setQuestion(Questions question) {
        this.question = question;
        if (!question.getAnswers().contains(this)) {
            question.getAnswers().add(this);
        }
    }

    @PrePersist
    public void prePersist() {
        this.answerDateTime = LocalDateTime.now();
    }

    public Questions getQuestion() {
        return question;
    }
}
