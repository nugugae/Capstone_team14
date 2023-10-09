package com.team14.sogeun.domain.dto;

import com.team14.sogeun.domain.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.team14.sogeun.domain.entity.User;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    private String role="USER"; ;  // set default value

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String loginId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    // 비밀번호 암호화 X
    public User toEntity() {
        UserRole userRole = (this.role != null) ? UserRole.valueOf(this.role) : UserRole.USER;

        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .role(userRole)  // 수정
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        UserRole userRole = (this.role != null) ? UserRole.valueOf(this.role) : UserRole.USER;

        return User.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .role(userRole) // 수정
                .build();
    }
}
