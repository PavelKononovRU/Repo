package com.exchange.payingservice.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Schema(name = "Валидация ошибок",description = "Ошибка операции")
@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    private final List<StatusCards> violations;
}
