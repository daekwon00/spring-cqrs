package kr.or.study.springcqrs.ai.chat.service.provider;

import kr.or.study.springcqrs.ai.chat.config.AiProviderProperties;
import kr.or.study.springcqrs.ai.chat.dto.web.ChatMessage;

import java.net.http.HttpRequest;
import java.util.List;

public interface AiProvider {

    String name();

    HttpRequest buildRequest(List<ChatMessage> messages, AiProviderProperties.ProviderConfig config) throws Exception;

    StreamParser parser();

    interface StreamParser {
        record ParseResult(String text, int promptTokens, int completionTokens, boolean isDone) {}

        ParseResult parseLine(String data);
    }
}
