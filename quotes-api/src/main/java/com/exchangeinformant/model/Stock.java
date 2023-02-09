package com.exchangeinformant.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Table(name = "stock")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность акции")
public class Stock {
    @Id
    @Column(name = "secure-code")
    @Schema(description = "SecureCode акции (тикер)", example = "AAPL")
    private String secureCode;

    @Column
    @Schema(description = "Название компании, выпустившей акцию", example = "Apple")
    private String issuer;

    @Column
    @Schema(description = "Валюта", example = "USD")
    private String currency;

    @OneToMany(mappedBy = "secureCode")
    @EqualsAndHashCode.Exclude
    @Schema(description = "Информация о акции")
    private List<Info> infoList;

    @Column
    @Schema(description = "Подключенный сервис для получения цен акций", example = "tinkoff")
    private String source;

    public Stock(String secureCode, String issuer, String currency,String source) {
        this.secureCode = secureCode;
        this.issuer = issuer;
        this.currency = currency;
        this.source=source;
    }
}
