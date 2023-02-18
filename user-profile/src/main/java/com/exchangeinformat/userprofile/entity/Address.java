package com.exchangeinformat.userprofile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
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

    @JsonIgnore
    @ManyToMany(mappedBy = "address")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Job> jobs;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

}
