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
    private Date create;

    @Column(name = "update_at")
    private Date update;

    @Column(name = "price")
    private Double price;

    @Column(name = "expenes_at")
    private Date expenes;

    @Column(name = "is_blocked")
    private Boolean blocked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return type.equals(product.type) && title.equals(product.title)
                && create.equals(product.create) && update.equals(product.update)
                && price.equals(product.price) && expenes.equals(product.expenes)
                && blocked.equals(product.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, create, update, price, expenes, blocked);
    }
}
