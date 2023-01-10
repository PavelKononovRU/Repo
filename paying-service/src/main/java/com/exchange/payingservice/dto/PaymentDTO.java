package com.exchange.payingservice.dto;

import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.entity.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    private Card card;

    private Date createAt;

    private Date updateAt;

    private Long user_id;

    private Payment.Status status;

    private String message;

    public enum Status {
        OK,
        DENIED,
        ERROR
    }
}
