package com.exchange.payingservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "principal")
    private String principal;

    @Column(name = "csv")
    private Long CSV;

    @Column(name = "user_id")
    private Long user_id;

    public Card(String number, String principal, Long CSV, Long user_id) {
        this.number = number;
        this.principal = principal;
        this.CSV = CSV;
        this.user_id = user_id;
    }

}