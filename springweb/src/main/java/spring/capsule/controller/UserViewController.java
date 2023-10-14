package spring.capsule.controller;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.capsule.domain.User;
import spring.capsule.service.UserService;

@Controller
public class UserViewController {//214 로그인,회원가입 경로 접근 시 뷰 파일을 연결

    @GetMapping("/index")
    public String index(){ return "index";}
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //join으로 바꾸자
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

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

        @GetMapping("/authentication-fail")
        public String authenticationFail() {
            return "errorPage/authenticationFail";
        }

        @GetMapping("/authorization-fail")
        public String authorizationFail() {
            return "errorPage/authorizationFail";
        }

    }*/
}