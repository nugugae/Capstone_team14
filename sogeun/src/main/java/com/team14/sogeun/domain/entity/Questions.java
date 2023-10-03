package com.team14.sogeun.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity//엔티티로 인식 & DB의 레코드와 매핑
//Lombok
@Builder// 객체를 생성하는 빌더 클래스를 자동으로 생성
@Getter //엔티티의 필드를 조회
@NoArgsConstructor //기본생성자 추가 (access = AccessLevel.PROTECTED) //접근제한
@AllArgsConstructor //모든 필드 값을 인자로 받는 생성자 자동 생성

@Table(name = "questions")
public class Questions {

    @ManyToOne(fetch = FetchType.LAZY) // 다(질문) - 1(user) 관계
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_Id;//질문 고유 ID

    @Column(name = "emotion", unique = true, nullable = false)
    private String emotion;//user의 감정 저장

    @Column(name = "question", columnDefinition = "TEXT", nullable = false)
    private String question;//user의 질문 내용 저장
    //@OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Questions> questions = new ArrayList<Questions>();

    @Column(name = "question_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime questionDate;//질문이 생성된 날짜 저장

    @Builder.Default
    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Answers answer;

    public Answers getAnswer() {//Answers 반환
        return answer;
    }
    public void setAnswer(Answers answer) {//Questions 객체에 Answers 객체를 연결
        if (this.answer != answer) {
            this.answer = answer;
            answer.updateQuestion(this);
        }
    }

    public void removeAnswer() { //Q와 A의 연결 제거
        if(this.answer != null) {
            this.answer.setQuestion(null);
            this.answer = null;
        }
    }


    public void setUser(User user) {
        this.user = user;
    }
}
