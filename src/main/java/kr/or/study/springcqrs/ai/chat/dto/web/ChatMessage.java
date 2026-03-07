package kr.or.study.springcqrs.ai.chat.dto.web;

public record ChatMessage(
        String role,
        String content
) {}
