package kr.or.study.springcqrs.ai.chat.api;

import kr.or.study.springcqrs.ai.chat.dto.web.AiChatWebRequest;
import kr.or.study.springcqrs.ai.chat.service.AiChatService;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class AiChatController implements AiChatApi {

    private final AiChatService aiChatService;

    @Override
    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chat(@RequestBody @Validated AiChatWebRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        return aiChatService.streamChat(userId, request);
    }
}
