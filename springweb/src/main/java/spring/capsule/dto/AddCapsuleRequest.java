package spring.capsule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.QnA;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCapsuleRequest {
    private String question;

    private String answer;

    public Capsule toEntity() {
        return Capsule.builder()
                .question(question)
                .answer(answer)
                .build();
    }
//    private List<QnA> qnaList;
//
//    public Capsule toEntity() {
//        Capsule capsule = Capsule.builder().build();
//        capsule.getQnaList().addAll(qnaList);
//        return capsule;
//    }
}
