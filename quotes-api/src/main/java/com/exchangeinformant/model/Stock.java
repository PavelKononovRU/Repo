package com.exchangeinformant.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Table(name = "stock")
@Entity
public class Stock {
    @Id
    @Column(name = "secure-code")
    @JsonSetter("securCode")
    private String secureCode;

    @Column
    @JsonSetter("shortName")
    private String issuer;

    @Column
    @JsonSetter("currencyCode")
    private String currency;

    @JsonIgnore
    @OneToMany(mappedBy = "secureCode")
    private List<Info> infoList;

}
