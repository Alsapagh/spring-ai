package com.spring.ai.demo.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ayman Alsapagh
 * Date :   8/27/2024
 */
@RestController
@RequestMapping("/ai")
public class AIController {

    private final ChatClient chatClient;

    public AIController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat/{message}")
    public String openAiCaht(@PathVariable("message") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/top-5-film/{actor}")
    public ActorFilms top5Films(@PathVariable("actor") String actor) {
        return chatClient.prompt()
                .user(userSpec -> userSpec.text("أفضل 5 أفلام ل{actor}")
                        .param("actor", actor))
                .call()
                .entity(ActorFilms.class);
    }

    public record ActorFilms(String actor, List<String> films) {
    }
}
