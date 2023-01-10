package com.exchangeinformant.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "stock")
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long company_id;
    @Column(name = "secure-code")
    private String secureCode;
    @Column(name = "issuer")
    private String name;
    @Column(name = "currency")
    private String currency;

    @OneToMany(mappedBy = "info")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<TinkoffStock> stocks;
}
