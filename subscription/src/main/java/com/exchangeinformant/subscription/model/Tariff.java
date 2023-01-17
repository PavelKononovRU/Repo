package com.exchangeinformant.subscription.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
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

}
