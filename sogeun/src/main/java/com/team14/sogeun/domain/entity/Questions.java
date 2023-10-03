package com.team14.sogeun.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter //엔티티의 필드를 조회
@NoArgsConstructor //기본생성자 추가 (access = AccessLevel.PROTECTED) //접근제한
@AllArgsConstructor
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "emotion", nullable = false)
    private String emotion;

    @Column(name = "question", columnDefinition = "TEXT", nullable = false)
    private String question;
    //@OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Questions> questions = new ArrayList<Questions>();

    @Column(name = "question_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime questionDate;

    @Builder.Default
    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answers> answers = new ArrayList<>();

    public List<Answers> getAnswers() {
        return answers;
    }

    public void addAnswer(Answers answer) {
        answers.add(answer);
        if (answer.getQuestion() != this) {
            answer.setQuestion(this);
        }
    }

    public void removeAnswer(Answers answer) {
        answers.remove(answer);
        if (answer.getQuestion() == this) {
            answer.setQuestion(null);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
