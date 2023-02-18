package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitListener {
    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = {"user.queue"})
    public void receiveMessage(UserInfo userInfo) {
        log.info(userInfo.toString());
    }
}
