package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "info")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlphaInfo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "last-price")
    @JsonProperty("05. price")
    private double lastPrice;

    @Column(name = "updated-at")
    private LocalDateTime updatedAt;

    @Column(name = "secure-code")
    @JsonProperty("01. symbol")
    private String secureCode;

}
