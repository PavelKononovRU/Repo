package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.Interval;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Mapper
public class SubscriptionDTO {
    private Long id;
    private Set<TariffDTO> tariff;
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
