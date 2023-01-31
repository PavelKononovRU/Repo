package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "stock")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @Column(name = "secure-code")
    private String secureCode;

    @Column
    private String issuer;

    @Column
    private String currency;

    @OneToMany(mappedBy = "secureCode")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private List<Info> infoList;

}
