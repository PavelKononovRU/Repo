package com.exchangeinformant.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 21:44
 */

@Data
@Component
@ConfigurationProperties(prefix = "alpha-vantage.api")
public class AlphaVantageConfig {
    private String key;
}
