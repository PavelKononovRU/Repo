package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="region")
    private String region;

    @Column(name="city")
    private String city;

    @Column(name="street")
    private String street;

    @Column(name="house")
    private String house;

    @Column(name="flat")
    private String flat;

    @ManyToMany(mappedBy = "address")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Job> jobs;

    @OneToOne(mappedBy = "address")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

}
