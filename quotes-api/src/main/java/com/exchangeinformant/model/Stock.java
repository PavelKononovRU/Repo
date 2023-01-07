package com.exchangeinformant.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Table(name = "stock")
@Entity
public class Stock {
    @Id
    @Column(name = "secur_code")
    private String secureCode;

    @Column
    private String issuer;

    @Column
    private double lastPrice;

    @Column
    private LocalDateTime updatedAt;
    @Column
    private String currency;



}
