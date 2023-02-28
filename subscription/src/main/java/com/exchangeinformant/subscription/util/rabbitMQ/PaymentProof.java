package com.exchangeinformant.subscription.util.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentProof {
    private long subscription_id;
    private String pay_amount;
    private String ext_id;
}
