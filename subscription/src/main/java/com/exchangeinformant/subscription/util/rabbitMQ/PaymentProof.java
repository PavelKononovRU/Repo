package com.exchangeinformant.subscription.util.rabbitMQ;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
