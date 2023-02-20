package com.exchangeinformant.subscription.model;


import com.exchangeinformant.subscription.util.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Schema(name = "Промоподписка", description = "Сущность промоподписки")
@Data
@Entity
@Table(name = "promosubscription")
public class PromoSubscription {

    @Id
    @Column(name = "promocode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PromoSubscription( Boolean isPromocodeApplied, String promocode, Type type) {
        this.isPromocodeApplied = isPromocodeApplied;
        this.promocode = promocode;
        this.type = type;
    }
    public PromoSubscription() {
    }

    @Column(name = "is_promocode_applied")
    private Boolean isPromocodeApplied;

    @Column(name = "promocode")
    private String promocode;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

}
