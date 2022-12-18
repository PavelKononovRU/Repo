package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @Column(name = "roles_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id.equals(roles.id);
    }

    @Override
    public int hashCode() {
        int result = 1;
        return 31 * result + ((id==null) ? 0 : id.hashCode());
    }

}
