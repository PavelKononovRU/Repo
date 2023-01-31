package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "name")
public class NameDTO implements Serializable {
    @Id
    @Column(name = "secure_code")
    @JsonProperty("secur_code")
    private String secureCode;

    @Column
    @JsonProperty("issuer")
    private String issuer;

    @Column
    @JsonProperty("currency")
    private String currency;
}
