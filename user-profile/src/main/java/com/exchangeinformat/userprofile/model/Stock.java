package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "previous_closures")
    private double previousClosures;

    @Column(name = "open")
    private double open;

    @Column(name = "volume")
    private double volume;

    @Column(name = "change_per_year")
    private double changePerYear;

    @Column(name = "capital")
    private double capital;

    @Column(name = "price")
    private double price;

    @Column(name = "dividends")
    private double dividends;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "profit")
    private double profit;

    @Column(name = "month_close")
    private double monthClose;

    @Column(name = "year_close")
    private double yearClose;

    @Column(name = "currency_code")
    private String currencyCode;
}
