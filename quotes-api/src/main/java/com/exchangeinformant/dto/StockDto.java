package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:52
 */

@Data
@NoArgsConstructor
public class StockDto {


    private double currentPrice;

    private String name;

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("01. symbol")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("price")
    public double getCurrentPrice() {
        return currentPrice;
    }

    @JsonProperty("05. price")
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

}
