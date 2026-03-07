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
public class AnthropicProvider implements AiProvider {

    private static final String API_URL = "https://api.anthropic.com/v1/messages";
    private static final String API_VERSION = "2023-06-01";

    private final ObjectMapper objectMapper;

    @Override
    public String name() {
        return "anthropic";
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
                .header("x-api-key", config.getApiKey())
                .header("anthropic-version", API_VERSION)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }

    @Override
    public StreamParser parser() {
        return data -> {
            try {
                JsonNode node = objectMapper.readTree(data);
                String type = node.path("type").asText();

                return switch (type) {
                    case "message_start" -> {
                        int pt = node.path("message").path("usage").path("input_tokens").asInt(0);
                        yield new StreamParser.ParseResult(null, pt, 0, false);
                    }
                    case "content_block_delta" -> {
                        String text = node.path("delta").path("text").asText("");
                        yield new StreamParser.ParseResult(text.isEmpty() ? null : text, 0, 0, false);
                    }
                    case "message_delta" -> {
                        int ct = node.path("usage").path("output_tokens").asInt(0);
                        yield new StreamParser.ParseResult(null, 0, ct, false);
                    }
                    case "message_stop" -> new StreamParser.ParseResult(null, 0, 0, true);
                    default -> new StreamParser.ParseResult(null, 0, 0, false);
                };
            } catch (Exception e) {
                log.debug("Anthropic 파싱 무시: {}", data);
                return new StreamParser.ParseResult(null, 0, 0, false);
            }
        };
    }
}
