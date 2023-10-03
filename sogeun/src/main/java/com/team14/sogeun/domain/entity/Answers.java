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
    private Long answer_Id;//답변의 고유 ID

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_Id")
    private Questions question;

    @Column(name = "answer_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime answerDateTime;//답변 생성 날짜 저장

    @Column(name = "answer", columnDefinition = "TEXT", nullable = false)
    private String answer;//답변 txt 저장

    public void setQuestion(Questions question) {// 답변을 특정 질문에 연결
        this.question = question;
        if (question.getAnswer() != this) {
            question.setAnswer(this);
        }
    }
    void updateQuestion(Questions question) {//무한 루프 회피 - 답변 호출 X
        this.question = question;
    }
    @PrePersist
    public void prePersist() {//entity가 DB저장 직전 호출 메소드-> 답변 생성날짜를 현재 시간으로 설정 
        this.answerDateTime = LocalDateTime.now();
    }
}
