package com.exchangeinformat.userprofile.model;

import com.exchangeinformat.userprofile.util.Interval;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
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
    private String status;

    @Column(name = "interval")
    @Enumerated(EnumType.STRING)
    private Interval interval;

    @Column(name = "interval_count")
    private int intervalCount;

    @Column(name = "price")
    private int price;

    @Column(name = "send_sms")
    private String sendSMS;
}
