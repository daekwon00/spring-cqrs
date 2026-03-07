package kr.or.study.springcqrs.ai.chat.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.study.springcqrs.ai.chat.dto.web.AiChatEvent;
import kr.or.study.springcqrs.ai.chat.dto.web.AiChatWebRequest;
import kr.or.study.springcqrs.ai.chat.dto.web.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AiChatService {

    private static final String ANTHROPIC_API_URL = "https://api.anthropic.com/v1/messages";
    private static final String ANTHROPIC_VERSION = "2023-06-01";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${ai.api-key}")
    private String apiKey;

    @Value("${ai.model}")
    private String model;

    @Value("${ai.max-tokens}")
    private int maxTokens;

    @Value("${ai.temperature}")
    private double temperature;

    public AiChatService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public SseEmitter streamChat(String userId, AiChatWebRequest request) {
        SseEmitter emitter = new SseEmitter(60_000L);

        String conversationId = request.conversationId() != null
                ? request.conversationId()
                : UUID.randomUUID().toString();

        CompletableFuture.runAsync(() -> {
            try {
                emitter.send(SseEmitter.event().data(AiChatEvent.start(conversationId)));

                String requestBody = buildRequestBody(request.messages());
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create(ANTHROPIC_API_URL))
                        .header("Content-Type", "application/json")
                        .header("x-api-key", apiKey)
                        .header("anthropic-version", ANTHROPIC_VERSION)
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                HttpResponse<java.io.InputStream> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofInputStream());

                if (response.statusCode() != 200) {
                    String errorBody = new String(response.body().readAllBytes(), StandardCharsets.UTF_8);
                    log.error("Anthropic API 오류: {} - {}", response.statusCode(), errorBody);
                    emitter.send(SseEmitter.event().data(AiChatEvent.error("AI 서비스 연결에 실패했습니다.")));
                    emitter.complete();
                    return;
                }

                int promptTokens = 0;
                int completionTokens = 0;

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.startsWith("data: ")) continue;
                        String data = line.substring(6).trim();
                        if (data.equals("[DONE]")) break;

                        JsonNode node = objectMapper.readTree(data);
                        String eventType = node.path("type").asText();

                        switch (eventType) {
                            case "message_start" -> {
                                JsonNode usage = node.path("message").path("usage");
                                promptTokens = usage.path("input_tokens").asInt(0);
                            }
                            case "content_block_delta" -> {
                                String text = node.path("delta").path("text").asText("");
                                if (!text.isEmpty()) {
                                    emitter.send(SseEmitter.event().data(AiChatEvent.delta(text)));
                                }
                            }
                            case "message_delta" -> {
                                JsonNode usage = node.path("usage");
                                completionTokens = usage.path("output_tokens").asInt(0);
                            }
                            default -> {}
                        }
                    }
                }

                emitter.send(SseEmitter.event().data(
                        AiChatEvent.done(new AiChatEvent.Usage(promptTokens, completionTokens))));
                emitter.complete();

            } catch (Exception e) {
                log.error("AI 채팅 스트리밍 오류", e);
                try {
                    emitter.send(SseEmitter.event().data(AiChatEvent.error("AI 서비스 오류가 발생했습니다.")));
                    emitter.complete();
                } catch (Exception ignored) {
                    emitter.completeWithError(e);
                }
            }
        });

        return emitter;
    }

    private String buildRequestBody(List<ChatMessage> messages) throws Exception {
        List<Map<String, String>> msgList = messages.stream()
                .map(m -> Map.of("role", m.role(), "content", m.content()))
                .toList();

        Map<String, Object> body = Map.of(
                "model", model,
                "max_tokens", maxTokens,
                "temperature", temperature,
                "stream", true,
                "messages", msgList
        );

        return objectMapper.writeValueAsString(body);
    }
}
