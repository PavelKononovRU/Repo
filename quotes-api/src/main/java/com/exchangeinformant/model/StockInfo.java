package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "stocks_info")
public class StockInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_info_id")
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

    @OneToMany(mappedBy = "stockInfo")
    private Set<Stock> stocks;
}
