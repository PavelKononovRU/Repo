package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.Interval;
import java.time.LocalDateTime;
import lombok.Data;
import org.mapstruct.Mapper;

import javax.validation.constraints.NotNull;

@Data
@Mapper
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
    private Subscription.Status status;
    @NotNull(message = "interval shouldn't be null")
    private Interval interval;
    @NotNull(message = "interval_count shouldn't be null")
    private int intervalCount;
    @NotNull(message = "price shouldn't be null")
    private int price;
    private int sendSMS;
}
