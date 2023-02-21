package com.exchangeinformant.subscription.service;


import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProof;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class MessageFromPayment {
    @Bean
    public Consumer<Message<PaymentProof>> getPaymentProof() {
        return (paymentProof) -> System.out.printf("Hi from listener! %s \n", paymentProof.getPayload());
    }
}
