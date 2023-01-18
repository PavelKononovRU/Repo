package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 07.01.2023
 * Time: 16:18
 */
@Table(name = "info")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name = "last-price")
    @JsonSetter("close")
    private double lastPrice;

    @Column(name = "updated-at")
    private LocalDateTime updatedAt;

    @Column(name = "secure-code")
    private String secureCode;

    @JsonGetter("price")
    public double getLastPrice() {
        return lastPrice;
    }

    public Info(double lastPrice, LocalDateTime updatedAt, String secureCode) {
        this.lastPrice = lastPrice;
        this.updatedAt = updatedAt;
        this.secureCode = secureCode;
    }
}
