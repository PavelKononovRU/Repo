package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratedColumn;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

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
    private long id;

    @Column(name = "last-price")
    @JsonSetter("close")
    private double lastPrice;

    @Column(name = "updated-at")
    private LocalDateTime updatedAt;

    @Column(name = "secure-code")
    private String secureCode;

    public Info(double lastPrice, String secureCode) {
        this.lastPrice = lastPrice;
        this.secureCode = secureCode;
    }

    public Info(double price, LocalDateTime now, String secureCode) {
    }
}
