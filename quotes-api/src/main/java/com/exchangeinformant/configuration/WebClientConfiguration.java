package com.exchangeinformant.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;




/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 23.12.2022
 * Time: 10:13
 */
@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClientWithTimeout() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
                .build();
    }
}
