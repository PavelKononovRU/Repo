package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "product_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "create_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updatedAt;

    @Column(name = "price")
    private Double price;

    @Column(name = "experies_at")
    private Date experiesedAt;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return type.equals(product.type) && title.equals(product.title)
                && createdAt.equals(product.createdAt) && updatedAt.equals(product.updatedAt)
                && price.equals(product.price) && experiesedAt.equals(product.experiesedAt)
                && isBlocked.equals(product.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, createdAt, updatedAt, price, experiesedAt, isBlocked);
    }
}
