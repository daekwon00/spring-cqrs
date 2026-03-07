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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeminiProvider implements AiProvider {

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/%s:streamGenerateContent?alt=sse&key=%s";

    private final ObjectMapper objectMapper;

    @Override
    public String name() {
        return "gemini";
    }

    @Override
    public HttpRequest buildRequest(List<ChatMessage> messages, AiProviderProperties.ProviderConfig config) throws Exception {
        // Gemini는 role을 "user"/"model"로 사용 (assistant → model)
        List<Map<String, Object>> contents = new ArrayList<>();
        for (ChatMessage msg : messages) {
            String role = "assistant".equals(msg.role()) ? "model" : msg.role();
            contents.add(Map.of(
                    "role", role,
                    "parts", List.of(Map.of("text", msg.content()))
            ));
        }

        Map<String, Object> requestBody = Map.of(
                "contents", contents,
                "generationConfig", Map.of(
                        "maxOutputTokens", config.getMaxTokens(),
                        "temperature", config.getTemperature()
                )
        );

        String url = String.format(API_URL, config.getModel(), config.getApiKey());
        String body = objectMapper.writeValueAsString(requestBody);

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }

    @Override
    public StreamParser parser() {
        return data -> {
            try {
                JsonNode node = objectMapper.readTree(data);
                JsonNode candidates = node.path("candidates");
                if (candidates.isArray() && !candidates.isEmpty()) {
                    JsonNode content = candidates.get(0).path("content").path("parts");
                    if (content.isArray() && !content.isEmpty()) {
                        String text = content.get(0).path("text").asText(null);
                        if (text != null) {
                            // usage 정보 추출
                            JsonNode usageMeta = node.path("usageMetadata");
                            int pt = usageMeta.path("promptTokenCount").asInt(0);
                            int ct = usageMeta.path("candidatesTokenCount").asInt(0);
                            return new StreamParser.ParseResult(text, pt, ct, false);
                        }
                    }
                    // finishReason 체크
                    String finishReason = candidates.get(0).path("finishReason").asText(null);
                    if ("STOP".equals(finishReason)) {
                        return new StreamParser.ParseResult(null, 0, 0, true);
                    }
                }
                return new StreamParser.ParseResult(null, 0, 0, false);
            } catch (Exception e) {
                log.debug("Gemini 파싱 무시: {}", data);
                return new StreamParser.ParseResult(null, 0, 0, false);
            }
        };
    }
}
