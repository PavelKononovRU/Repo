package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 08.01.2023
 * Time: 16:04
 */

@Data
public class InfoDTO {

    @JsonSetter("close")
    private BigDecimal lastPrice;

}
