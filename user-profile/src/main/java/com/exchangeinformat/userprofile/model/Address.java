package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity

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
    private List<Job> jobs;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(region, address.region) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(house, address.house) && Objects.equals(flat, address.flat);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((id==null) ? 0 : id.hashCode());
        result = 31 * result + ((region==null) ? 0 : region.hashCode());
        result = 31 * result + ((city==null) ? 0 : city.hashCode());
        result = 31 * result + ((street==null) ? 0 : street.hashCode());
        result = 31 * result + ((house==null) ? 0 : house.hashCode());
        result = 31 * result + ((flat==null) ? 0 : flat.hashCode());
        return result;    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                '}';
    }
}
