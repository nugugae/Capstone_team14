package spring.capsule.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {//UserDetails 상속받아 인증객체로 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "nickname",unique = true, nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Builder
    public User(String email ,String nickname, String password,UserRole role, String auth) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    //권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }


    @Override
    public String getUsername() {//사용자 id반환
        return email;
    }

    //@Override
    public String getNickname() {//사용자 id반환
        return nickname;
    }

    @Override
    public String getPassword() {//사용자 비번 반환
        return password;
    }
//@Override
    public UserRole getRole(){ return  role;}

    @Override
    public boolean isAccountNonExpired() { //계정 만료여부 반환
        return true;//만료 X , false 만료0
    }

    @Override
    public boolean isAccountNonLocked() {//계쩡 잠금 여부
        return true;//잠금되지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {//비번 만료 반환
        return true;
    }

    @Override
    public boolean isEnabled() {//계정 사용가능여부 반환
        return true;
    }
}