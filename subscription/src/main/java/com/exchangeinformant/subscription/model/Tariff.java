package com.exchangeinformant.subscription.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Schema(name = "Тариф", description = "Сущность тарифа")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tariff tariff = (Tariff) o;
        return id != null && Objects.equals(id, tariff.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Tariff() {
    }
}
