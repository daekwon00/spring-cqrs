package kr.or.study.springcqrs.ai.chat.dto.web;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AiChatWebRequest(
        @NotEmpty List<ChatMessage> messages,
        String conversationId
) {}
