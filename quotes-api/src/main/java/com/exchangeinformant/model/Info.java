package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
@Schema(description = "Информация о акции")
public class Info {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name = "last-price")
    private BigDecimal lastPrice;

    @Column(name = "updated-at")
    @Schema(description = "Дата и время полученной цены", type = "string", format = "date-time", example = "2023-02-03T12:00:00.000000")
    private LocalDateTime updatedAt;

    @Column(name = "secure-code")
    @Schema(description = "SecureCode акции (тикер)", example = "AAPL")
    private String secureCode;

    @JsonGetter("price")
    @Schema(description = "Цена акции", type = "string", example = "150,00")
    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public Info(BigDecimal lastPrice, LocalDateTime updatedAt, String secureCode) {
        this.lastPrice = lastPrice;
        this.updatedAt = updatedAt;
        this.secureCode = secureCode;
    }
}
