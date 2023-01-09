package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long company_id;

    @Column(name = "secure-code")
    private String secureCode;


    @JsonProperty("Name")
    @Column(name = "issuer")
    private String name;

    @JsonProperty("Currency")
    @Column(name = "currency")
    private String currency;


    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<TinkoffStock> stocks;
}
