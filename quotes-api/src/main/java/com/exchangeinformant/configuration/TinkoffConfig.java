package com.exchangeinformant.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
@EnableConfigurationProperties(TinkoffData.class)
@RequiredArgsConstructor
public class TinkoffConfig {

    private final TinkoffData tinkoffData;

    @Bean
    public OpenApi openApi() {
        return new OkHttpOpenApi(tinkoffData.getSandbox(), tinkoffData.getIsSandboxMode());
    }
}
