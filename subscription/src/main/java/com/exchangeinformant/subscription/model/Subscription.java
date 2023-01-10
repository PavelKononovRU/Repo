package com.exchangeinformant.subscription.model;

import com.exchangeinformant.subscription.util.Interval;
import com.exchangeinformant.subscription.util.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
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

    @Column(name = "interval")
    @Enumerated(EnumType.STRING)
    private Interval interval;

    @Column(name = "interval_count")
    private int intervalCount;

    @Column(name = "price")
    private int price;

    @Column(name = "send_sms")
    private int sendSMS;

//    @Column(name="is_promo")
//    private Boolean isPromo;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "promosubscription_id")
//    private PromoSubscription promoSubscription;
//
    public enum Status {
        AWAITING_TRANSACTION,
        ACTIVE,
        INACTIVE,
        PAYMENT_ERROR,
        AWAITING_DEACTIVATION,
        DEACTIVATION_ERROR,
        AWAITING_START_DATE,
        ARCHIVED
    }
}
