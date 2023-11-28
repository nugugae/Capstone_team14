package spring.capsule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.capsule.domain.User;
import spring.capsule.domain.UserRole;

@NoArgsConstructor
@Getter
public class UserViewResponse {
    private String email;
    private String nickname;
    private UserRole role;
    public UserViewResponse(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getRole();
    }
}
