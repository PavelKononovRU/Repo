package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.previousClosures, previousClosures) == 0 &&
                Double.compare(stock.open, open) == 0 && Double.compare(stock.volume, volume) == 0 &&
                Double.compare(stock.changePerYear, changePerYear) == 0 && Double.compare(stock.capital, capital) == 0 &&
                Double.compare(stock.price, price) == 0 && Double.compare(stock.dividends, dividends) == 0 &&
                Double.compare(stock.profit, profit) == 0 && Double.compare(stock.monthClose, monthClose) == 0 &&
                Double.compare(stock.yearClose, yearClose) == 0 && shortName.equals(stock.shortName) &&
                currencyCode.equals(stock.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousClosures, open, volume, changePerYear, capital, price, dividends, shortName, profit, monthClose, yearClose, currencyCode);
    }
}
