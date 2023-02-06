package com.exchangeinformant.model;
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
    @EqualsAndHashCode.Exclude
    private List<Info> infoList;

    @Column
    private String source;

    public Stock(String secureCode, String issuer, String currency,String source) {
        this.secureCode = secureCode;
        this.issuer = issuer;
        this.currency = currency;
        this.source=source;
    }
}
