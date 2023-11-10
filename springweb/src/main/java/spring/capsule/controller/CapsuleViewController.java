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
import spring.capsule.domain.User;
import spring.capsule.dto.AddCapsuleRequest;
import spring.capsule.dto.CapsuleListViewResponse;
import spring.capsule.dto.CapsuleViewResponse;
import spring.capsule.service.CapsuleService;
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
    @Value("${openai.api.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;
    @Value("${openai.api.key}")
    private String apikey;

    // 전체 캡슐
    @GetMapping("/capsules")
    public String getCapsules(Model model, Principal principal) {
        // Get logged-in user's email or username from principal
        String email = principal.getName();
        User user = userService.findByEmail(email);

        // Fetch capsules by user ID
        Map<LocalDate, List<CapsuleViewResponse>> capsulesByDate =
                capsuleService.findAllByUserIdGroupedByDate(user.getUid());
        model.addAttribute("capsules", capsulesByDate);

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
    public String newCapsule(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("capsule", new CapsuleViewResponse());

            model.addAttribute("model", this.model);
            model.addAttribute("apiURL", this.apiURL);
            model.addAttribute("apikey", this.apikey);
        } else {
            Capsule capsule = capsuleService.findById(id);
            model.addAttribute("capsule", new CapsuleViewResponse(capsule));
        }

        return "chat";
    }



    @PostMapping("/capsule/chat")
    public ResponseEntity<Capsule> saveCapsule(@RequestBody AddCapsuleRequest request, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        Capsule savedCapsule = capsuleService.save(request,user.getUid());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCapsule);

    }






}
