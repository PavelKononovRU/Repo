package com.exchangeinformant.subscription.model;

import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Subscription(Tariff tariff, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startAt, LocalDateTime expiresAt, Status status, Interval interval, int intervalCount, int price, int sendSMS, Long userId) {
        this.tariff = tariff;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startAt = startAt;
        this.expiresAt = expiresAt;
        this.status = status;
        this.interval = interval;
        this.intervalCount = intervalCount;
        this.price = price;
        this.sendSMS = sendSMS;
        this.userId = userId;
    }

    public Subscription() {
    }

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

    @Column(name = "user_id")
    private Long userId;

}
