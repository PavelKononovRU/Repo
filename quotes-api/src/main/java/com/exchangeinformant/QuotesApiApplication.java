package com.exchangeinformant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
        info = @Info(
                title = "Quotes API",
                version = "beta",
                description = "API для получения акций и цен акций"
        ),
        servers = @Server(url = "/quotes")
)
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
@EnableFeignClients
public class QuotesApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuotesApiApplication.class, args);
    }

}
