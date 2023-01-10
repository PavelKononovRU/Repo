package com.exchange.payingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudPaymentDTO {

    private Long card_id;

    private String phone;

    private String email;

    //Item под-класс с amount и subscription_id
    private Map<String, String> items;

    private String promocode;


}
