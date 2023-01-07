package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

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
public class Info {
    @Id
    @Column
    private long id;

    @Column(name = "last-price")
    @JsonSetter("close")
    private double lastPrice;

    @Column(name = "updated-at")
    private LocalDateTime updatedAt;

    @Column(name = "secure-code")
    private String secureCode;


}
