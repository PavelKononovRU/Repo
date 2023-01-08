package com.exchangeinformant.model;

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
    private long id;

    @Column(name = "last-price")
    @JsonSetter("close")
    private double lastPrice;

    @Column(name = "updated-at")
    private LocalDateTime updatedAt;

    @Column(name = "secure-code")
    private String secureCode;


}
