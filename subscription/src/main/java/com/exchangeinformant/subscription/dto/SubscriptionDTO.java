package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.Interval;
import java.time.LocalDateTime;
import lombok.Data;
import org.mapstruct.Mapper;

@Data
@Mapper
public class SubscriptionDTO {
    private Long id;
    private Tariff tariff;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime startAt;
    private LocalDateTime expiresAt;
    private Subscription.Status status;
    private Interval interval;
    private int intervalCount;
    private int price;
    private String sendSMS;
}
