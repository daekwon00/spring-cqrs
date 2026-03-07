package kr.or.study.springcqrs.ai.chat.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "AI 채팅 요청")
public record AiChatWebRequest(
        @Schema(description = "AI 프로바이더 (anthropic, openai, gemini). 미지정 시 서버 기본값 사용",
                example = "anthropic", allowableValues = {"anthropic", "openai", "gemini"})
        String provider,

        @NotEmpty
        @Schema(description = "대화 메시지 목록")
        List<ChatMessage> messages,

        @Schema(description = "대화 ID (신규 대화 시 생략)")
        String conversationId
) {}
