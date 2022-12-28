package com.exchangeinformant.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
@EnableConfigurationProperties(ApiConfig.class)
@RequiredArgsConstructor
public class TinkoffConfig {
    private final ApiConfig apiConfig;
    private final String token = "t.OlKXgNUlgSJ9X1JGcJ8J_ABQcVhJJ6wdMYTZB6Owyx0zqA5i1MoWPcm-3b1ma_XrZtj5OooL7AI2GEQXZgZeMg";
    private final boolean isModeOn = true;

    @Bean
    public OpenApi openApi() {
        return new OkHttpOpenApi(apiConfig.getSandbox(), apiConfig.getIsSandboxMode());
    }
}
