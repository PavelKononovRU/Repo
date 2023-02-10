package com.exchangeinformant.subscription.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;


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

    public Tariff(String title, String description, String type, Boolean isActive) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.isActive = isActive;
    }

    public Tariff() {
    }
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="type")
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
}
