package com.exchange.payingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Schema(name = "Внешняя заглушка для платежей")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StubPaymentDTO {

    @Schema(description = "Идентификатор платежа")
    private Long card_id;

    @Schema(description = "Номер телефона")
    @NotNull
    private String phone;

    @Schema(description = "Эл.почта")
    @NotNull
    private String email;

    //Item под-класс с amount и subscription_id
    @Schema(description = "Сумма и идентификатор подписки")
    private Map<String, String> items;

    @Schema(description = "Промокод")
    @NotNull
    private String promocode;


}
