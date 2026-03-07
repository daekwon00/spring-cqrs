package kr.or.study.springcqrs.ai.chat.service;

import kr.or.study.springcqrs.ai.chat.config.AiProviderProperties;
import kr.or.study.springcqrs.ai.chat.dto.web.AiChatEvent;
import kr.or.study.springcqrs.ai.chat.dto.web.AiChatWebRequest;
import kr.or.study.springcqrs.ai.chat.service.provider.AiProvider;
import kr.or.study.springcqrs.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AiChatService {

    private final HttpClient httpClient;
    private final AiProviderProperties properties;
    private final Map<String, AiProvider> providers;

    public AiChatService(AiProviderProperties properties, List<AiProvider> providerList) {
        this.httpClient = HttpClient.newHttpClient();
        this.properties = properties;
        this.providers = providerList.stream()
                .collect(Collectors.toMap(AiProvider::name, Function.identity()));
    }

    public SseEmitter streamChat(String userId, AiChatWebRequest request) {
        String providerName = resolveProvider(request.provider());
        AiProvider provider = providers.get(providerName);
        if (provider == null) {
            throw new BusinessException("지원하지 않는 AI 프로바이더: " + providerName, HttpStatus.BAD_REQUEST);
        }

        AiProviderProperties.ProviderConfig config = getConfig(providerName);
        if ("not-configured".equals(config.getApiKey())) {
            throw new BusinessException(providerName + " API 키가 설정되지 않았습니다.", HttpStatus.SERVICE_UNAVAILABLE);
        }

        SseEmitter emitter = new SseEmitter(60_000L);
        String conversationId = request.conversationId() != null
                ? request.conversationId()
                : UUID.randomUUID().toString();

        CompletableFuture.runAsync(() -> {
            try {
                emitter.send(SseEmitter.event().data(AiChatEvent.start(conversationId, providerName)));

                HttpRequest httpRequest = provider.buildRequest(request.messages(), config);
                HttpResponse<java.io.InputStream> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofInputStream());

                if (response.statusCode() != 200) {
                    String errorBody = new String(response.body().readAllBytes(), StandardCharsets.UTF_8);
                    log.error("{} API 오류: {} - {}", providerName, response.statusCode(), errorBody);
                    emitter.send(SseEmitter.event().data(AiChatEvent.error("AI 서비스 연결에 실패했습니다.")));
                    emitter.complete();
                    return;
                }

                AiProvider.StreamParser parser = provider.parser();
                int promptTokens = 0;
                int completionTokens = 0;

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.startsWith("data: ")) continue;
                        String data = line.substring(6).trim();
                        if (data.isEmpty()) continue;

                        AiProvider.StreamParser.ParseResult result = parser.parseLine(data);

                        if (result.text() != null) {
                            emitter.send(SseEmitter.event().data(AiChatEvent.delta(result.text())));
                        }
                        if (result.promptTokens() > 0) promptTokens = result.promptTokens();
                        if (result.completionTokens() > 0) completionTokens = result.completionTokens();
                        if (result.isDone()) break;
                    }
                }

                emitter.send(SseEmitter.event().data(
                        AiChatEvent.done(new AiChatEvent.Usage(promptTokens, completionTokens))));
                emitter.complete();

            } catch (Exception e) {
                log.error("{} 채팅 스트리밍 오류", providerName, e);
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

    private String resolveProvider(String requested) {
        if (requested != null && !requested.isBlank()) {
            return requested.toLowerCase();
        }
        return properties.getDefaultProvider();
    }

    private AiProviderProperties.ProviderConfig getConfig(String providerName) {
        return switch (providerName) {
            case "anthropic" -> properties.getAnthropic();
            case "openai" -> properties.getOpenai();
            case "gemini" -> properties.getGemini();
            default -> throw new BusinessException("지원하지 않는 AI 프로바이더: " + providerName, HttpStatus.BAD_REQUEST);
        };
    }
}
