package com.team14.sogeun.controller;

import com.team14.sogeun.domain.entity.Answers;
import com.team14.sogeun.domain.entity.Questions;
import com.team14.sogeun.service.AnswerService;
import com.team14.sogeun.service.OpenCapsuleService;
import com.team14.sogeun.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/capsule")
public class QnAController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final OpenCapsuleService opencapsule;
    @PostMapping("/submit")
    public ResponseEntity<Map<String, String>> submitAnswer(@RequestBody Map<String, String> payload) {
        String userAnswertxt = payload.get("answer");
        String gptQuestiontxt = payload.get("question");

        // 질문 저장
        Questions gptQuestion = new Questions();
        gptQuestion.setQuestion(gptQuestiontxt);
        gptQuestion.setQuestionDate(LocalDateTime.now());
        questionService.saveQuestion(gptQuestiontxt);

        // 답변 저장
        Answers answer = new Answers();
        answer.setAnswer(userAnswertxt);
        answer.setAnswerDate(LocalDateTime.now());
        answerService.saveAnswer(userAnswertxt);

        //   실제 GPT API 호출로 대체
        String newGptQuestion = "GPT에서 생성된 새로운 질문...";

        // 새로운 질문을 응답으로 전송
        Map<String, String> response = new HashMap<>();
        response.put("newQuestion", newGptQuestion);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/open")//캡슐 열기
    public ResponseEntity<Map<Questions, Answers>> getQnAOlderThanAWeek(@RequestParam Long userId) {
        Map<Questions, Answers> qna = opencapsule.getQnAOlderThanAWeek(userId);
        return ResponseEntity.ok(qna);
    }
    @GetMapping("/lastEmotions")
    public String viewLastWeekEmotions(Model model, @RequestParam Long userId) {
        // 지난주 감정 데이터를 가져와서 model에 추가
        List<String> emotionW = questionService.getUserEmotionsLastWeek(userId);//지난주
        List<String> emotionM = questionService.getUserEmotionsLastMonth(userId);//한 달
        model.addAttribute("emotionW", emotionW);
        model.addAttribute("emotionM", emotionM);
        return "lastEmotions";
    }
    @GetMapping("/AllQnAforGPT")
    public ResponseEntity<String> getAllQnAForGPT(@RequestParam Long userId) {
        List<Questions> questions = questionService.getQuestionsByUser(userId);
        StringBuilder dataForGPT = new StringBuilder();

        for (Questions question : questions) {
            Answers answer = answerService.getAnswerByQuestionId(question.getQuestionId());
            dataForGPT.append("Question: ").append(question.getQuestion()).append("\n");
            dataForGPT.append("Answer: ").append(answer.getAnswer()).append("\n\n");
        }

        // 이제 dataForGPT에 모든 질문과 답변 정보가 들어 있습니다.
        // 여기서는 단순히 응답으로 반환하지만 실제로는 GPT 호출을 위한 추가 작업이 필요합니다.
        return ResponseEntity.ok(dataForGPT.toString());
    }
}

/*
    @GetMapping("/capsule")
    public String capsulePage(Model model) {
        // GPT로부터의 질문을 가져와 model에 추가
        Questions gptQuestion = questionService.getLatestQuestion(); // 가정: getLatestQuestion()는 최신 질문을 가져옴
        //Answers userAnswer= AnswerService.get()
        model.addAttribute("gptQuestion", gptQuestion);
        return "capsule";  // capsule.html 페이지를 반환
    }

    @PostMapping("/capsule")
    public String submitAnswer(@RequestParam Long questionId, @RequestParam String userAnswer) {
        Answers answer = new Answers();
        answer.setQuestion(questionService.findById(questionId));
        answer.setAnswer(userAnswer);
        answerService.saveAnswer(answer);
        return "redirect:/capsule";
    }
*/

