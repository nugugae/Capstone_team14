package spring.capsule.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qna")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "capsule_id")
    private Capsule capsule;

    public QnA(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}

