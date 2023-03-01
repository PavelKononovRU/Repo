package com.exchangeinformant.mq;

import com.exchangeinformant.model.UserInfo;
import com.exchangeinformant.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Slf4j
public class MessageProcessor {

    private final UserInfoRepository userInfoRepository;

    public MessageProcessor(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Bean
    public Function<String, UserInfo> processor() {
        return extId -> userInfoRepository.findById(extId).orElse(null);
    }
}
