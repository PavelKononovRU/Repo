package com.exchangeinformant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
@EnableFeignClients
public class QuotesApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuotesApiApplication.class, args);
    }

}
