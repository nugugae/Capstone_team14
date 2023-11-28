package spring.capsule.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.capsule.domain.User;
import spring.capsule.dto.UserViewResponse;
import spring.capsule.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class UserViewController {//214 로그인,회원가입 경로 접근 시 뷰 파일을 연결
    private final UserService userService;

    @GetMapping("/home")
    public String home(Model model, Authentication auth) {
        if (auth != null) {
            // 현재 로그인한 사용자의 이름(이메일)을 가져옵니다.
            String loggedInUsername = auth.getName();

            // 로그인한 사용자의 정보를 가져옵니다.
            User loggedInUser = userService.findByEmail(loggedInUsername);

            if (loggedInUser != null) {
                // User 객체를 UserViewResponse 객체로 변환합니다.
                UserViewResponse userView = new UserViewResponse(loggedInUser);

                // 변환된 사용자 정보를 뷰에 전달합니다.
                model.addAttribute("user", userView);
            }
        }
        return "home";
    }


    @GetMapping("/info")
    public String getUserInfo(Model model, Authentication auth) {
        if (auth != null) {
            // 현재 로그인한 사용자의 이름(아이디)를 가져옵니다.
            String loggedInUsername = auth.getName();

            // 로그인한 사용자의 정보를 가져옵니다.
            User loggedInUser = userService.findByEmail(loggedInUsername);

            if (loggedInUser != null) {
                // User 객체를 UserViewResponse 객체로 변환합니다.
                UserViewResponse userView = new UserViewResponse(loggedInUser);

                // 변환된 사용자 정보를 뷰에 전달합니다.
                model.addAttribute("user", userView);
            }
        }

        // info.html 뷰를 렌더링합니다.
        return "info";

    }




    @GetMapping({"/",""})
    public String index(){ return "index";}
    @GetMapping("/login")
    public String login() {  return "login";  }

    //join으로 바꾸자
    @GetMapping("/signup")
    public String signup() {  return "signup";  }

    /*
    @GetMapping("/info")
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String userInfo(Model model, Authentication auth) {

        User loginUser = UserService.getLoginUserByLoginId(auth.getName());
        model.addAttribute("user", loginUser);

        return "info";

        @GetMapping("/admin")
        //@PreAuthorize("hasAuthority('ADMIN')")
        public String adminPage() {
            return "admin";
        }
*/
    @GetMapping("/authentication-fail")
    public String authenticationFail() { return "errorPage/authenticationFail";}

    @GetMapping("/authorization-fail")
    public String authorizationFail() { return "errorPage/authorizationFail"; }


}