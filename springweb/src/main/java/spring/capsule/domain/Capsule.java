package spring.capsule.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
//
//    @ElementCollection
//    private List<QnA> qnaList = new ArrayList<>();


    @CreatedDate
    @Column(name = "qnadate")
    private LocalDate qnadate;

    //유저와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    //빌더 패턴으로 객체 생성
    @Builder
    public Capsule(String question, String answer, LocalDate qnadate) {
        this.question = question;
        this.answer = answer;
        this.qnadate = qnadate;
    }
    //public Capsule(List<QnA> qnaList) {  this.qnaList = qnaList;    }

    public void setQnadate(LocalDate qnadate) {
        this.qnadate = qnadate;
    }
    public  void setUser(User user){
        this.user =user;
        user.getCapsules().add(this);
    }
//    public void update(String question, String answer,LocalDate qnadate) {
//        this.question = question;
//        this.answer = answer;
//        this.qnadate = qnadate;
//    }
}
