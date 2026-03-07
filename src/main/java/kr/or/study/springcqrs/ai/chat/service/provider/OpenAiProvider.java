package kr.or.study.springcqrs.ai.chat.service.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.study.springcqrs.ai.chat.config.AiProviderProperties;
import kr.or.study.springcqrs.ai.chat.dto.web.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiProvider implements AiProvider {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private final ObjectMapper objectMapper;

    @Override
    public String name() {
        return "openai";
    }

    @Override
    public HttpRequest buildRequest(List<ChatMessage> messages, AiProviderProperties.ProviderConfig config) throws Exception {
        List<Map<String, String>> msgList = messages.stream()
                .map(m -> Map.of("role", m.role(), "content", m.content()))
                .toList();

        String body = objectMapper.writeValueAsString(Map.of(
                "model", config.getModel(),
                "max_tokens", config.getMaxTokens(),
                "temperature", config.getTemperature(),
                "stream", true,
                "messages", msgList
        ));

        return HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + config.getApiKey())
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }

    @Override
    public StreamParser parser() {
        return data -> {
            try {
                if ("[DONE]".equals(data.trim())) {
                    return new StreamParser.ParseResult(null, 0, 0, true);
                }
                JsonNode node = objectMapper.readTree(data);
                JsonNode choices = node.path("choices");
                if (choices.isArray() && !choices.isEmpty()) {
                    JsonNode delta = choices.get(0).path("delta");
                    String text = delta.path("content").asText(null);
                    if (text != null) {
                        return new StreamParser.ParseResult(text, 0, 0, false);
                    }
                }
                // usage (마지막 청크에 포함될 수 있음)
                JsonNode usage = node.path("usage");
                if (!usage.isMissingNode()) {
                    int pt = usage.path("prompt_tokens").asInt(0);
                    int ct = usage.path("completion_tokens").asInt(0);
                    return new StreamParser.ParseResult(null, pt, ct, false);
                }
                return new StreamParser.ParseResult(null, 0, 0, false);
            } catch (Exception e) {
                log.debug("OpenAI 파싱 무시: {}", data);
                return new StreamParser.ParseResult(null, 0, 0, false);
            }
        };
    }
}
