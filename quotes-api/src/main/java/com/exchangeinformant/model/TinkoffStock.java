package com.exchangeinformant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
public class TinkoffStock {
   private String ticker;
   private String figi;
   private String name;
   private String type;
   private Currency currency;
   private BigDecimal lastPrice;
}
