package com.team14.sogeun.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity//엔티티로 인식 & DB의 레코드와 매핑
@Builder// 객체를 생성하는 빌더 클래스를 자동으로 생성
@Getter //엔티티의 필드를 조회
@NoArgsConstructor//기본생성자 추가 (access = AccessLevel.PROTECTED) //접근제한
@AllArgsConstructor//모든 필드 값을 인자로 받는 생성자 자동 생성
@Table(name = "gpt_questions")
public class Questions {
    @Id@Column(name ="question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_Id;//질문 고유 ID

    @ManyToOne// 다(질문) - 1(user) 관계
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "emotion")
    private String emotion;//user의 감정 저장

    @Column(name = "question", columnDefinition = "TEXT", nullable = false)
    private String question;//user의 질문 내용 저장
    //@OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Questions> questions = new ArrayList<Questions>();

    @Column(name = "question_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime questionDate;//질문이 생성된 날짜 저장


    //Answers 반환
    @Getter
    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
    private Answers answer;

    public void setAnswer(Answers answer) {//Questions 객체에 Answers 객체를 연결
        if (this.answer != answer) {
            this.answer = answer;
            //answer.updateQuestion(this);
        }
    }

    public void setQuestion(String gptQuestion) {
        this.question = gptQuestion;
    }

    public void setQuestionDate(LocalDateTime now) {
        this.questionDate = now;
    }
    public Long getQuestionId() {  return this.question_Id;    }


}
