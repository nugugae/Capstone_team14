package spring.capsule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/capsules")
    public String getCapsules(Model model) {
        List<CapsuleListViewResponse> capsules = capsuleService.findAll()
                .stream()
                .map(CapsuleListViewResponse::new)
                .toList();
        model.addAttribute("capsules", capsules);

        return "capsuleList";
    }

    @GetMapping("/capsules/{id}")
    public String getCapsule(@PathVariable Long id, Model model) {
        Capsule capsule = capsuleService.findById(id);
        model.addAttribute("capsule", new CapsuleViewResponse(capsule));

        return "capsule";
    }


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

//
//    @PostMapping("/capsule/chat")
//    public String saveCapsule(@ModelAttribute AddCapsuleRequest request, Model model) {
//        Capsule savedCapsule = capsuleService.save(request);
//        model.addAttribute("capsule", new CapsuleViewResponse(savedCapsule));
//        return "redirect:/capsules/" ; // 저장된 캡슐의 상세 페이지로 리다이렉트
//    }


    @PostMapping("/capsule/chat")
    public ResponseEntity<Capsule> saveCapsule(@RequestBody AddCapsuleRequest request, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        Capsule savedCapsule = capsuleService.save(request,user.getUid());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCapsule);
//        try {
//            // Logic to either append to today's capsule or create a new one if it doesn't exist
//            Capsule savedCapsule = capsuleService.saveOrUpdateTodayCapsule(request);
//            // Return a successful response with the saved capsule data
//            return ResponseEntity.ok(new CapsuleViewResponse(savedCapsule));
//        } catch (Exception e) {
//            // Handle exceptions and return an appropriate error response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
    }


    @GetMapping("/capsules/by-date")
    public String getCapsulesGroupedByDate(Model model) {
        Map<LocalDate, List<CapsuleViewResponse>> capsulesByDate = capsuleService.findAllGroupedByDate();
        model.addAttribute("capsulesByDate", capsulesByDate);
        return "capsuleListByDate";
    }

//
//    @GetMapping("/capsules/date/{date}")
//    public String getCapsulesByDate(@PathVariable String date, Model model) {
//        LocalDate localDate = LocalDate.parse(date);
//        List<Capsule> capsules = capsuleService.findByDate(localDate);
//        model.addAttribute("capsules", capsules);
//        return "capsuleListByDate";
//    }




}
