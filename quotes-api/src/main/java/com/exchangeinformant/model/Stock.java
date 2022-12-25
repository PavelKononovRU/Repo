package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @JsonProperty("01. symbol")
    @Column(name = "symbol")
    private String symbol;

    @JsonProperty("02. open")
    @Column(name = "open")
    private String open;

    @JsonProperty("03. high")
    @Column(name = "high")
    private String high;

    @JsonProperty("04. low")
    @Column(name = "low")
    private String low;

    @JsonProperty("05. price")
    @Column(name = "price")
    private String price;

    @JsonProperty("06. volume")
    @Column(name = "volume")
    private String volume;

    @JsonProperty("07. latest trading day")
    @Column(name = "latest_trading_day")
    private String latestTradingDay;

    @JsonProperty("08. previous close")
    @Column(name = "previous_close")
    private String previousClose;

    @JsonProperty("09. change")
    @Column(name = "change")
    private String change;

    @JsonProperty("10. change percent")
    @Column(name = "change_percent")
    private String changePercent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stock_info_id")
    private StockInfo stockInfo;
}
