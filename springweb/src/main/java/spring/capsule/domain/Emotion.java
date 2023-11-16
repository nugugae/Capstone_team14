package spring.capsule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "emotion")
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid", updatable = false)
    private Long mid;
    @Column(name = "mood")
    private String mood;

    //유저와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

    @CreatedDate
    @Column(name = "mdate")
    private LocalDate mdate;

    //빌더 패턴으로 객체 생성
    @Builder
    public Emotion(User user ,String mood ,LocalDate mdate) {
        this.user = user;
        this.mood = mood;
        this.mdate = mdate;
    }

    public void setMdate(LocalDate mdate) {
        this.mdate = mdate;
    }
    public  void setUser(User user) {
        this.user = user;
        user.getEmotions().add(this);

    }



}
