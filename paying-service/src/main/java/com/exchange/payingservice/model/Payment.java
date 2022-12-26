package com.exchange.payingservice.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "payment_table")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "card_id", foreignKey = @ForeignKey(name = "card"))
    private Card card;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "status")
    private Status status;

    @Column(name = "message")
    private String message;

    public enum Status{
        OK,
        DENIED,
        ERROR
    }
}

