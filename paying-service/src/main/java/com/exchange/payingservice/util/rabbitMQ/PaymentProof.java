package com.exchange.payingservice.util.rabbitMQ;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentProof {
    private long subscription_id;
    private String pay_amount;
    private String ext_id;
}
