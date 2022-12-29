package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SubscriptionDTO {
    private Long id;
    private Tariff tariff;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime startAt;
    private LocalDateTime expiresAt;
    private Status status;
    private Interval interval;
    private int intervalCount;
    private int price;
    private int sendSMS;
    private Long userId;
}
