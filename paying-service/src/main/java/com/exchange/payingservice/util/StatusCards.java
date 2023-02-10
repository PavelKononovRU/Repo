package com.exchange.payingservice.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Статус операции с картой", description = "Статус результата выполнения операции с картой.")
@Data
@AllArgsConstructor
public class StatusCards {

    @Schema(description = "Результат операции", example = "Карта с номером 2222-0000-1111-2222 сохранена")
    private String title;

    @Schema(description = "Сообщение пользователю", example = "Управляйте картами в платежной информации")
    private String user_message;

    @Schema(description = "Дата операции", example = "created at 2023-02-10T11:01:00.2208922")
    private String timeStamp;

}

