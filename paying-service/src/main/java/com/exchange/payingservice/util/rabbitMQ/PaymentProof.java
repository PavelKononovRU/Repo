package com.exchange.payingservice.util.rabbitMQ;

import lombok.*;


@Data
@NoArgsConstructor
public class PaymentProof {
    private long subscription_id;
    private String pay_amount;
    private String ext_id;

    @Builder
    public PaymentProof(long subscription_id, String pay_amount, String ext_id) {
        this.subscription_id = subscription_id;
        this.pay_amount = pay_amount;
        this.ext_id = ext_id;
    }
}
