package com.team14.sogeun.domain.entity;

import com.team14.sogeun.domain.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "web_users")
public class User {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;
    private String nickname;

    private UserRole role;

    // Questions와의 관계 설정
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Questions> questions = new ArrayList<Questions>();

    // 관계 설정을 위한 편의 메서드
    /*
    public void addQuestion(Questions question) {
        questions.add(question);
        question.setUser(this);
    }

    public void removeQuestion(Questions question) {
        questions.remove(question);
        question.setUser(null);
    }*/
}
