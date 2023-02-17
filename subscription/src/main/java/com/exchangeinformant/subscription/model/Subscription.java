package com.exchangeinformant.subscription.model;

import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Schema(name = "Подписка", description = "Сущность подписки")
@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "interval_format")
    @Enumerated(EnumType.STRING)
    private Interval interval;

    @Column(name = "interval_count")
    private int intervalCount;

    @Column(name = "price")
    private int price;

    @Column(name = "send_sms")
    private int sendSMS;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_promo")
    private Boolean isPromo;

    @OneToOne
    @JoinColumn(name = "promocode_id")
    private PromoSubscription promoSubscription;


    public Subscription() {

    }
}
