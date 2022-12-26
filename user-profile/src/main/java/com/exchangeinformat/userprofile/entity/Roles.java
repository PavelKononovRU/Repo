package com.exchangeinformat.userprofile.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @Column(name = "roles_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
