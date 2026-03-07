package kr.or.study.springcqrs.ai.chat.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.ai.chat.dto.web.AiChatWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Tag(name = "AI Chat", description = "AI 채팅 API")
public interface AiChatApi {

    @Operation(summary = "AI 채팅 (SSE 스트리밍)")
    SseEmitter chat(AiChatWebRequest request);
}
