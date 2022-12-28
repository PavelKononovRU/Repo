package com.exchangeinformant.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "tinkoff.api")
@Component
public class ApiConfig {
    private String sandbox;
    private Boolean isSandboxMode;
}
