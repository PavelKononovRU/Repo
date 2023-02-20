package com.exchangeinformant.subscription.service;



import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProof;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Service
public class MessageFromPayment implements Consumer<Message<PaymentProof>> {

    @Override
    public void accept(Message<PaymentProof> paymentProofMessage) {
        System.out.println("Received payment: " + paymentProofMessage.getPayload());
    }
}
