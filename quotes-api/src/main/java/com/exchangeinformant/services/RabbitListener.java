//package com.exchangeinformant.services;
//
//import com.exchangeinformant.repository.UserInfoRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class RabbitListener {
//    private final RabbitTemplate rabbitTemplate;
//    private final UserInfoRepository userInfoRepository;
//
//    public RabbitListener(RabbitTemplate rabbitTemplate, UserInfoRepository userInfoRepository) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.userInfoRepository = userInfoRepository;
//    }
//
//    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = {"stock.queue"})
//    public void receiveMessage(String id) {
//        log.info("User with id has been received - " +id);
//        if(userInfoRepository.findById(id).isPresent()){
//            rabbitTemplate.convertAndSend("stock.ex","response",userInfoRepository.findById(id).get());
//        }else {
//            log.info("No info under such id");
//        }
//    }
//}
