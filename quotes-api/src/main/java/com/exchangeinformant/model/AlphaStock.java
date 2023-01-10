package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "info")
public class AlphaStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonProperty("01. symbol")
    @Column(name = "secure-code")
    private String symbol;

    @JsonProperty("05. price")
    @Column(name = "last-price")
    private String price;


    @Column(name = "updated-at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Info info;
}
