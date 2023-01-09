package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "info")
public class TinkoffStock {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "secure-code")
   private String ticker;
   @Column(name = "last-price")
   private BigDecimal lastPrice;
   @Column(name = "updated-at")
   private LocalDateTime updatedAt;

   @JsonIgnore
   @ManyToOne
   @JoinColumn(name = "secure-code")
   private Company company;

   public TinkoffStock(String ticker, BigDecimal lastPrice, LocalDateTime updatedAt) {
      this.ticker = ticker;
      this.lastPrice = lastPrice;
      this.updatedAt = updatedAt;
   }
}
