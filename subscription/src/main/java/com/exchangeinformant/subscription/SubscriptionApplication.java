package com.exchangeinformant.subscription;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
        info = @Info(
                title = "Subscription API",
                version = "beta",
                description = "API для CRUD операций с подписками."
        ),
        servers = @Server(url = "/subscription")
)
@SpringBootApplication
@EnableDiscoveryClient
public class SubscriptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubscriptionApplication.class, args);
    }

}
