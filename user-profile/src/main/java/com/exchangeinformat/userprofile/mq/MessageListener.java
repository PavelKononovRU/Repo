package com.exchangeinformat.userprofile.mq;

import com.exchangeinformat.userprofile.entity.UserInfo;
import com.exchangeinformat.userprofile.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class MessageListener {

    private final UserInfoRepository userInfoRepository;

    public MessageListener(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Bean
    public Consumer<UserInfo> userInfoConsumer() {
        return (userInfo) -> {
            userInfo.setLastRequest(LocalDateTime.now());
            userInfoRepository.save(userInfo);
            log.info(userInfo.toString());
        };
    }
}
