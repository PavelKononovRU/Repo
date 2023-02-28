package com.exchange.payingservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(
        info = @Info(
                title = "Paying-service API",
                version = "beta",
                description = "API для CRUD операций с картами и платежами."
        ),
        servers = @Server(url = "/paying-service")
)
@SpringBootApplication
@EnableScheduling
public class PayingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayingServiceApplication.class, args);
    }
}
