package com.exchangeinformat.userprofile.model;

import com.exchangeinformat.userprofile.util.Interval;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "subscriptions_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription subscription = (Subscription) o;
        return status.equals(subscription.status) && interval.equals(subscription.interval)
                && sendSMS.equals(subscription.sendSMS);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((id==null) ? 0 : id.hashCode());
        result = 31 * result + ((status==null) ? 0 : status.hashCode());
        result = 31 * result + ((interval==null) ? 0 : interval.hashCode());
        result = 31 * result + ((sendSMS==null) ? 0 : sendSMS.hashCode());
        return result;
    }

}
