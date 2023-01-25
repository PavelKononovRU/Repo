package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.model.PromoSubscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Mapper
@Validated
public class SubscriptionDTO {
    private Long id;
    @NotNull(message = "tariff shouldn't be null")
    private Tariff tariff;
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime createdAt;
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime updatedAt;
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime startAt;
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime expiresAt;
    @NotNull(message = "status shouldn't be null")
    private Status status;
    @NotNull(message = "interval shouldn't be null")
    private Interval interval;
    @NotNull(message = "interval_count shouldn't be null")
    private int intervalCount;
    @NotNull(message = "price shouldn't be null")
    private int price;
    private int sendSMS;
    private int userId;
    private Boolean isPromo;
    private PromoSubscription promoSubscription;
}
