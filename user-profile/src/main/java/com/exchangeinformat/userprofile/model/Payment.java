package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "payments")
@Getter
@Setter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "status")
    private Status status;
    @Column(name = "message")
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id) && card.equals(payment.card) && Objects.equals(createAt, payment.createAt) && Objects.equals(updateAt, payment.updateAt) && user.equals(payment.user) && Objects.equals(message, payment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, card, createAt, updateAt, user, message);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", card=" + card +
                ", create_at=" + createAt +
                ", update_at=" + updateAt +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }

    public enum Status{
        OK,
        DENIED,
        ERROR
    }
}
