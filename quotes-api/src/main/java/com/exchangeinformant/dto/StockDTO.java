package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

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
