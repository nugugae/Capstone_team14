package spring.capsule.dto;

import lombok.Getter;
import lombok.Setter;
import spring.capsule.domain.UserRole;
//import javax.validation.constraints.NotBlank;
@Setter
@Getter
public class AddUserRequest {//사용자정보객체
    //@NotBlank(message = "메일주소가 비어있습니다.")
    private String email;
    private String password;
    private String nickname;
    private UserRole role;

}