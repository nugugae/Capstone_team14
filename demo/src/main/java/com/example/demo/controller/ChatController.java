package com.example.demo.controller;

import com.example.demo.entity.ChatMessage;
import com.example.demo.repositoty.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/")
    public String chatPage(Model model) {
        List<ChatMessage> messages = chatMessageRepository.findAllByOrderByTimestampDesc();
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String sender, @RequestParam String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);
        chatMessage.setMessage(message);
        chatMessage.setTimestamp(String.valueOf(LocalDateTime.now()));
        chatMessageRepository.save(chatMessage);
        return "redirect:/";
    }
}


