package com.team14.sogeun.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.team14.sogeun.domain.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements UserDetails {

    private User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 권한 관련 작업을 하기 위한 role return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(() -> {
            return user.getRole().name();
        });

        return collections;
    }

    // get Password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // get Username
    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    // true: 계정 만료X
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // true: 계정 잠기지 않음
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // true: 비밀번호 만료X
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // true: 계정 사용가능
    @Override
    public boolean isEnabled() {
        return true;
    }


}
