package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
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

    @Column(name="type")
    private String type;

    @Column(name = "is_active")
    private Boolean isActive;
}
