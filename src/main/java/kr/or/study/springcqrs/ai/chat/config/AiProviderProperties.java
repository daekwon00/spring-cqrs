package kr.or.study.springcqrs.ai.chat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ai")
public class AiProviderProperties {

    private String defaultProvider = "anthropic";
    private ProviderConfig anthropic = new ProviderConfig();
    private ProviderConfig openai = new ProviderConfig();
    private ProviderConfig gemini = new ProviderConfig();

    @Getter
    @Setter
    public static class ProviderConfig {
        private String apiKey = "not-configured";
        private String model;
        private int maxTokens = 1024;
        private double temperature = 0.7;
    }
}
