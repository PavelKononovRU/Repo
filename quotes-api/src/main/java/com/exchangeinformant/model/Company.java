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
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @JsonProperty("Symbol")
    @Column(name = "symbol")
    private String symbol;

    @JsonProperty("AssetType")
    @Column(name = "asset_type")
    private String assetType;

    @JsonProperty("Name")
    @Column(name = "name")
    private String name;

    @JsonProperty("Currency")
    @Column(name = "currency")
    private String currency;

    @JsonProperty("Country")
    @Column(name = "country")
    private String country;

    @JsonProperty("Sector")
    @Column(name = "sector")
    private String sector;

    @JsonProperty("Industry")
    @Column(name = "industry")
    private String industry;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Stock> stocks;
}
