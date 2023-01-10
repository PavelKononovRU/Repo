package com.exchangeinformant.subscription.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "promosubscription")
public class PromoSubscription {
    @Id
    @Column(name = "promosubscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promocode")
    private String promocode;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        PROCENT,
        SUMM
    }
}
