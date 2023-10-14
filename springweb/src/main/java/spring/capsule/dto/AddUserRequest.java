package spring.capsule.dto;

import lombok.Getter;
import lombok.Setter;
import spring.capsule.domain.UserRole;

@Setter
@Getter
public class AddUserRequest {//사용자정보객체
    private String email;
    private String password;
    private String nickname;
    private UserRole role;
}