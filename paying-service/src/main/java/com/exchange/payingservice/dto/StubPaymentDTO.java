package com.exchange.payingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StubPaymentDTO {

    private Long card_id;
    @NotNull
    private String phone;
    @NotNull
    private String email;

    //Item под-класс с amount и subscription_id
    private Map<String, String> items;
    @NotNull
    private String promocode;


}
