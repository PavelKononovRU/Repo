package com.exchange.payingservice.dto;

import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.util.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    @Valid
    private Card card;
    @PastOrPresent
    private Date createAt;
    @PastOrPresent
    private Date updateAt;
    @NotNull
    private Long user_id;

    private Status status;

    private String message;

}
