package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.Column;
import lombok.Data;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 08.01.2023
 * Time: 16:30
 */
@Data
public class StockDTO {

    @JsonSetter("securCode")
    private String secureCode;

    @Column
    @JsonSetter("shortName")
    private String issuer;

    @JsonSetter("currencyCode")
    private String currency;

    @JsonSetter("info")
    private InfoDTO infoList;
}
