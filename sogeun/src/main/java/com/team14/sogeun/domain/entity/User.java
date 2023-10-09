package com.team14.sogeun.domain.entity;

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
    @Column(name = "login_id", unique = true, nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(length = 255, nullable = false)
    private UserRole role;

    // Questions와의 관계 설정
    @Builder.Default

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
