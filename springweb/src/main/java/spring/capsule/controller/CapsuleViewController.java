package spring.capsule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import spring.capsule.domain.Capsule;
import spring.capsule.dto.CapsuleListViewResponse;
import spring.capsule.dto.CapsuleViewResponse;
import spring.capsule.service.CapsuleService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CapsuleViewController {
    private final CapsuleService capsuleService;

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
        } else {
            Capsule capsule = capsuleService.findById(id);
            model.addAttribute("capsule", new CapsuleViewResponse(capsule));
        }

        return "chat";
    }
}
