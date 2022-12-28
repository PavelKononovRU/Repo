package com.exchangeinformant.subscription.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tariff")
public class Tariff {
    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToOne(mappedBy = "tariff")
//    private Set<Subscription> subscription;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="type")
    private String type;

    @Column(name = "is_active")
    private Boolean isActive;

}
