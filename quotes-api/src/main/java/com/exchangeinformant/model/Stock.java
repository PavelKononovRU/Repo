package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "stocks_alpha")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    public Long id;

    @JsonProperty("01. symbol")
    @Column(name = "symbol")
    public String symbol;

    @JsonProperty("02. open")
    @Column(name = "open")
    public String open;

    @JsonProperty("03. high")
    @Column(name = "high")
    public String high;

    @JsonProperty("04. low")
    @Column(name = "low")
    public String low;

    @JsonProperty("05. price")
    @Column(name = "price")
    public String price;

    @JsonProperty("06. volume")
    @Column(name = "volume")
    public String volume;

    @JsonProperty("07. latest trading day")
    @Column(name = "latest_trading_day")
    public String latestTradingDay;

    @JsonProperty("08. previous close")
    @Column(name = "previous_close")
    public String previousClose;

    @JsonProperty("09. change")
    @Column(name = "change")
    public String change;

    @JsonProperty("10. change percent")
    @Column(name = "change_percent")
    public String changePercent;

    @Column(name = "created_at")
    public LocalDateTime createdAt;
}
