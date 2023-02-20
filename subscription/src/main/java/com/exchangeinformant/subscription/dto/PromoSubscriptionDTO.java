package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.util.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mapstruct.Mapper;

@Mapper
@Data
@Schema(name = "Промоподписка", description = "Сущность промоподписки")
public class PromoSubscriptionDTO {
    @Schema(description = "Идентификатор промоподписки")
    private Long id;
    @Schema(description = "Активность промоподписки")
    private Boolean isPromocodeApplied;
    @Schema(description = "Промокод")
    private String promocode;
    @Schema(description = "Тип промокода")
    private Type type;
}
