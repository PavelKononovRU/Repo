package com.exchangeinformant.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockClientInterceptor implements RequestInterceptor {
    private final BcsConfig bcsConfig;

    public StockClientInterceptor(BcsConfig bcsConfig) {
        this.bcsConfig = bcsConfig;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("partner-token", bcsConfig.getPartnerToken());
    }
}
