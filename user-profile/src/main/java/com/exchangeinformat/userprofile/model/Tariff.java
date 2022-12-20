package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    @Column(name="type")
    private String type;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(title, tariff.title) && Objects.equals(description, tariff.description) &&
                Objects.equals(type, tariff.type) && Objects.equals(isActive, tariff.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, type, isActive);
    }
}
