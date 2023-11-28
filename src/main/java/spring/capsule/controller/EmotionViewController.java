package spring.capsule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import spring.capsule.domain.User;
import spring.capsule.service.EmotionService;
import spring.capsule.service.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//웹 페이지를 렌더링 @Controller
@RequiredArgsConstructor
@Controller
public class EmotionViewController {
    private final UserService userService;
    private final EmotionService emotionService;
    @Value("${openai.api.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;
    @Value("${openai.api.key}")
    private String apikey;


    //@GetMapping("/emotions")





}
