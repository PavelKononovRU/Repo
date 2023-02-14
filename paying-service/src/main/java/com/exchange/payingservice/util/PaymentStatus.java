package com.exchange.payingservice.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(name = "Статус операции с платежом", description = "Статус платежа")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatus {

    @Schema(description = "Статус платежа", example = "SUCCESSFULLY")
    private Status status;

    @Schema(description = "Сообщение пользователю", example = "Ваш платеж успешно принят.")
    private String user_message;
}
