package com.exchangeinformant.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 05.01.2023
 * Time: 17:13
 */
@Data
@Component
@ConfigurationProperties(prefix = "bcs.api")
public class BcsConfig {
    private String url;

    private String partnerToken;

    private String oneStock;
}
