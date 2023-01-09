package com.exchangeinformant.dto;


import lombok.Data;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:52
 */

@Data
public class StockDTO {
    private String name;
    private String currentPrice;

    public StockDTO(String name, String currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }
}
