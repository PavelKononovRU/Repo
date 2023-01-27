package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NameDTO {
    @JsonProperty("secur_code")
    private String secureCode;

    @JsonProperty("issuer")
    private String issuer;

    @JsonProperty("currency")
    private String currency;
}
