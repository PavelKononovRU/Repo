package com.exchangeinformant.subscription.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

@Data
@Mapper
@Validated
@Schema(name = "Тарифы", description = "Тарифы на подписки")
public class TariffDTO {
    @Schema(description = "Идентификатор тарифа")
    private Long id;
    @NotNull(message = "title shouldn't be null")
    @Schema(description = "Наименование тарифа")
    private String title;
    @NotNull(message = "description shouldn't be null")
    @Schema(description = "Описание тарифа")
    private String description;
    @NotNull(message = "type shouldn't be null")
    @Schema(description = "Тип тарифа")
    private String type;
    @Schema(description = "Активность тарифа")
    private Boolean isActive;
}
