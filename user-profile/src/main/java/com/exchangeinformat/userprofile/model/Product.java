package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "product_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
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

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<User> userList;

}
