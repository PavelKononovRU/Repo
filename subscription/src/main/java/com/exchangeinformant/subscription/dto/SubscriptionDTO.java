package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.model.PromoSubscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Mapper
@Validated
@Schema(name = "Подписка", description = "Сущность подписки")
public class SubscriptionDTO {
    @Schema(description = "Идентификатор подписки")
    private Long id;
    @Schema(description = "Тариф подписки")
    @NotNull(message = "tariff shouldn't be null")
    private Tariff tariff;
    @Schema(description = "Дата создания подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime createdAt;
    @Schema(description = "Дата изменения подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime updatedAt;
    @Schema(description = "Дата активации подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime startAt;
    @Schema(description = "Дата истечения подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime expiresAt;
    @Schema(description = "Статус подписки")
    @NotNull(message = "status shouldn't be null")
    private Status status;
    @Schema(description = "Формат интервала")
    @NotNull(message = "interval shouldn't be null")
    private Interval interval;
    @Schema(description = "Размер интервала")
    @NotNull(message = "interval_count shouldn't be null")
    private int intervalCount;
    @Schema(description = "Цена")
    @NotNull(message = "price shouldn't be null")
    private int price;
    @Schema(description = "Отправка смс")
    private int sendSMS;
    @Schema(description = "Идентификатор владельца")
    private int userId;
    @Schema(description = "Действительность промоподписки")
    private Boolean isPromo;
    @Schema(description = "Промоподписка")
    private PromoSubscription promoSubscription;
}
