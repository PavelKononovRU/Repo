package com.exchangeinformat.userprofile.mq;

import com.exchangeinformat.userprofile.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class MessageListener {

    @Bean
    public Consumer<UserInfo> userInfoConsumer() {
        return (userInfo) -> {
            log.info(userInfo.toString());
        };
    }
}
