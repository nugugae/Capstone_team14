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
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//웹 페이지를 렌더링 @Controller
@RequiredArgsConstructor
@Controller
public class CapsuleViewController {
    private final CapsuleService capsuleService;
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(CapsuleViewController.class);


    @Value("${openai.api.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;
    @Value("${openai.api.key}")
    private String apikey;

    // 전체 캡슐
    @GetMapping("/capsules")
    public String getCapsules(Model model) {
        Map<LocalDate, List<CapsuleViewResponse>> capsulesByDate = capsuleService.findAllGroupedByDate();
        model.addAttribute("capsulesByDate", capsulesByDate);

        return "capsuleList";
    }

    //해당 날짜 보기
    @GetMapping("/capsule/date/{date}")
    public String getCapsulesByUserAndDate(@PathVariable Long userId, @PathVariable String date, Model model) {
        LocalDate localDate = LocalDate.parse(date);
        List<Capsule> capsules = capsuleService.findAllByUidAndQnadate(userId, localDate);
        model.addAttribute("capsules", capsules.stream().map(CapsuleViewResponse::new).collect(Collectors.toList()));

//    @GetMapping("/capsule/{date}")
//    public String getCapsulesByDate(@PathVariable String date, Model model) {
//        LocalDate localDate = LocalDate.parse(date);
//        List<Capsule> capsules = capsuleService.findAllByUserAndDate(localDate);
//        model.addAttribute("capsules", capsules.stream().map(CapsuleViewResponse::new).collect(Collectors.toList()));
//
        return "capsule";
    }


    //채팅

    @GetMapping("/capsule/chat")
    public String newCapsule(@RequestParam(required = false) Long userId,LocalDate date, Model model) {
        if (date != null) {
            List<Capsule> capsulesForDate = capsuleService.findAllByUidAndQnadate(userId,date);
            List<Map<String, String>> conversation = capsulesForDate.stream()
                    .flatMap(capsule -> Stream.of(
                            Map.of("role", "user", "content", capsule.getQuestion()),
                            Map.of("role", "assistant", "content", capsule.getAnswer())
                    ))
                    .collect(Collectors.toList());
            log.info("Preloaded conversation: {}", conversation); // 로그 추가
            // Add the preloaded conversation to the model so it can be used in the view
            model.addAttribute("preloadedConversation", conversation);
        } else {
            // Handle the case where no date is provided, and a new conversation is started
            model.addAttribute("capsule", new CapsuleViewResponse());
        }

        // Include additional model attributes as needed for the chat page
        model.addAttribute("model", this.model);
        model.addAttribute("apiURL", this.apiURL);
        model.addAttribute("apikey", this.apikey);

        return "chat";
    }
//    @GetMapping("/capsule/chat")
//    public String newCapsule(@RequestParam(required = false) Long id, Model model) {
//        if (id == null) {
//            model.addAttribute("capsule", new CapsuleViewResponse());
//
//            model.addAttribute("model", this.model);
//            model.addAttribute("apiURL", this.apiURL);
//            model.addAttribute("apikey", this.apikey);
//        } else {
//            Capsule capsule = capsuleService.findById(id);
//            model.addAttribute("capsule", new CapsuleViewResponse(capsule));
//        }
//
//        return "chat";
//    }



    @PostMapping("/capsule/chat")
    public ResponseEntity<Capsule> saveCapsule(@RequestBody AddCapsuleRequest request, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        Capsule savedCapsule = capsuleService.save(request,user.getUid());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCapsule);

    }






}
