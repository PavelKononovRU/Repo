package com.example.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SuccessHandler successHandler;

    @Autowired
    public SecurityConfig(SuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ServerLogoutSuccessHandler handler) {
        http
                .authorizeExchange()
                .pathMatchers("/actuator/**", "/")
                .permitAll()
                .and()
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login()
                .authenticationSuccessHandler(successHandler)
                .and()
                .logout()
                .logoutSuccessHandler(handler)
                .and().csrf().disable();

        return http.build();
    }

    @Bean
    public ServerLogoutSuccessHandler keycloakLogoutSuccessHandler(ReactiveClientRegistrationRepository repository) {

        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(repository);

        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/api/user/home");

        return oidcLogoutSuccessHandler;
    }
}
