package spring.capsule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.Emotion;
import spring.capsule.domain.User;
import spring.capsule.dto.*;
import spring.capsule.service.CapsuleService;
import spring.capsule.service.EmotionService;
import spring.capsule.service.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//웹 페이지를 렌더링 @Controller
@RequiredArgsConstructor
@Controller
public class CapsuleViewController {
    private final CapsuleService capsuleService;
    private final UserService userService;
    private final EmotionService emotionService;
    @Value("${openai.api.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;
    @Value("${openai.api.key}")
    private String apikey;


    //감정
    @GetMapping("/emotions")
    public String getEmotions(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Map<LocalDate, List<EmotionViewResponse>> emotions= emotionService.findAllByUserIdGroupedByDate(user.getUid());
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(7);
        LocalDate monthAgo = today.minusDays(30);

        List<String> lastWeekEmotions = emotions.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(weekAgo) && entry.getKey().isBefore(today))
                .flatMap(entry -> entry.getValue().stream())
                .map(EmotionViewResponse::getMood)
                .collect(Collectors.toList());

        List<String> lastMonthEmotions= emotions.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(monthAgo) && entry.getKey().isBefore(today))
                .flatMap(entry -> entry.getValue().stream())
                .map(EmotionViewResponse::getMood)
                .collect(Collectors.toList());
        model.addAttribute("lastWeekEmotions", lastWeekEmotions);
        model.addAttribute("lastMonthEmotions", lastMonthEmotions);

        return "emotions";
    }
    // 전체 캡슐
    @GetMapping("/capsules")
    public String getCapsules(Model model, Principal principal) {
        // Get logged-in user's email or username from principal
        String email = principal.getName();
        User user = userService.findByEmail(email);

        // Fetch capsules by user ID
        Map<LocalDate, List<CapsuleViewResponse>> sortedCapsulesByDate =
                capsuleService.findAllByUserIdGroupedByDate(user.getUid());
        model.addAttribute("capsulesByDate", sortedCapsulesByDate);

        return "capsuleList";
    }
    //해당 날짜 보기
    @GetMapping("/capsule/{date}")
    public String getCapsulesByDate(@PathVariable String date, Model model, Principal principal) {
        LocalDate localDate = LocalDate.parse(date);
        String email = principal.getName();
        User user = userService.findByEmail(email);

        // Fetch all capsules by date and user ID
        List<Capsule> capsules = capsuleService.findAllByUserIdAndDate(user.getUid(), localDate);

        // Pass the entire list of capsules to the view, even if it's empty
        model.addAttribute("capsules", capsules);
        model.addAttribute("date", date); // Pass the date to the model as well

        return "capsule";
    }
    //채팅
    @GetMapping("/capsule/chat")
    public String getChat(@RequestParam(required = false) Long id, Model model, Principal principal) {
        if (id != null) {
            // ID로 캡슐 조회
            Capsule capsule = capsuleService.findById(id);
            model.addAttribute("capsule", new CapsuleViewResponse(capsule));
        } else {
            // 새 캡슐 생성
            model.addAttribute("capsule", new CapsuleViewResponse());
        }

        // 현재 사용자의 이메일 또는 사용자 이름 가져오기
        String email = principal.getName();
        User user = userService.findByEmail(email);

        // 현재 날짜로 채팅 내용 조회
        LocalDate today = LocalDate.now();
        List<Capsule> todaysChat = capsuleService.findChatByDate(user.getUid(), today);
        model.addAttribute("todaysChat", todaysChat);

        // 추가적인 모델 속성
        model.addAttribute("model", this.model);
        model.addAttribute("apiURL", this.apiURL);
        model.addAttribute("apikey", this.apikey);

        return "chat";
    }


    @PostMapping("/capsule/chat")
    public ResponseEntity<Capsule> saveCapsule(@RequestBody AddCapsuleRequest request, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Capsule savedCapsule = capsuleService.save(request, user.getUid());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCapsule);
    }

    @PostMapping("/capsule/saveSentiment")
    public ResponseEntity<Emotion> saveEmotion(@RequestBody AddCapsuleRequest request, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Emotion savedEmotion = emotionService.save(request, user.getUid());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmotion);
    }


}
