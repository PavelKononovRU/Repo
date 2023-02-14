package com.exchange.payingservice.dto;

import com.exchange.payingservice.util.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(name = "Платеж", description = "Сущность платежа")
@Data
@NoArgsConstructor
public class PaymentDTO {

    @Schema(description = "Идентификатор платежа")
    private Long id;

    @Schema(description = "Сущность карты")
    @Valid
    private CardDTO cardDTO;

    @Schema(description = "Дата создания")
    @PastOrPresent
    private Date createAt;

    @Schema(description = "Дата обновления")
    @PastOrPresent
    private Date updateAt;

    @Schema(description = "Идентификатор пользователя")
    @NotNull
    private Long user_id;

    @Schema(description = "Статус платежа")
    private Status status;

    @Schema(description = "Комментарий по платежу")
    private String message;

}
