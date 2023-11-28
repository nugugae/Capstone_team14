package spring.capsule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spring.capsule.domain.Message;
import spring.capsule.dto.ChatGPTRequest;
import spring.capsule.dto.ChatGptResponse;
import spring.capsule.repository.ChatRepository;

import java.util.List;

@Controller
@RequestMapping("/bot")
public class CustomBotController {

    @Value("${openai.api.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;//RestTemplate객체 주입
/*

//
//    @GetMapping("/chat")//사용자가 url로 get요청시 chat 메서드 호출
//    public String chat(@RequestParam("prompt") String prompt) { //사용자의 prompt를 받아 OpenAI에 요청을 보내고 받은 응답 문자열로 반환
//        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
//        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
//        return chatGptResponse.getChoices().get(0).getMessage().getContent();
//    }

    @Autowired
    private ChatRepository ChatRepository;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("prompt") String prompt) {
        // 사용자의 메시지를 데이터베이스에 저장
        ChatRepository.save(new Message("user", prompt));

        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);

        // GPT의 응답을 데이터베이스에 저장
        String response = chatGptResponse.getChoices().get(0).getMessage().getContent();
        ChatRepository.save(new Message("gpt", response));

        return "chat";
    }
//    @GetMapping("/chat")
//    public String chatPage(Model model) {
//        model.addAttribute("pageName", "Chat with GPT");
//        return "chat";
//    }
    @GetMapping("/chat/history")
    public List<Message> getChatHistory() {
        return ChatRepository.findAll();
    }


*/

}