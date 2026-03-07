package kr.or.study.springcqrs.ai.chat.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AiChatEvent(
        String type,
        String content,
        String conversationId,
        String message,
        Usage usage
) {
    public static AiChatEvent start(String conversationId) {
        return new AiChatEvent("start", null, conversationId, null, null);
    }

    public static AiChatEvent delta(String content) {
        return new AiChatEvent("delta", content, null, null, null);
    }

    public static AiChatEvent done(Usage usage) {
        return new AiChatEvent("done", null, null, null, usage);
    }

    public static AiChatEvent error(String message) {
        return new AiChatEvent("error", null, null, message, null);
    }

    public record Usage(int promptTokens, int completionTokens) {}
}
