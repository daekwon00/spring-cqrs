package kr.or.study.springcqrs.ai.chat.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AiChatEvent(
        String type,
        String content,
        String conversationId,
        String provider,
        String message,
        Usage usage
) {
    public static AiChatEvent start(String conversationId, String provider) {
        return new AiChatEvent("start", null, conversationId, provider, null, null);
    }

    public static AiChatEvent delta(String content) {
        return new AiChatEvent("delta", content, null, null, null, null);
    }

    public static AiChatEvent done(Usage usage) {
        return new AiChatEvent("done", null, null, null, null, usage);
    }

    public static AiChatEvent error(String message) {
        return new AiChatEvent("error", null, null, null, message, null);
    }

    public record Usage(int promptTokens, int completionTokens) {}
}
