package com.exchangeinformant.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    private List<Info> infoList;

}
