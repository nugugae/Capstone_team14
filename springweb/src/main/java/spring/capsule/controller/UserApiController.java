package spring.capsule.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.capsule.dto.AddUserRequest;
import spring.capsule.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserApiController {//213  signup가입 폼에서 회원가입 요청 시 서비스 메서드를 사용해 사용자 저장하고 로그인 페이지로 이동

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);//회원가입 메서드호출
        return "redirect:/login";// 회원가입  완료 후 로그인 페이지로
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
