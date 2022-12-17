package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "inn")
    private String inn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "users_products",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Column(name = "token")
    private String token;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(middleName, user.middleName) && Objects.equals(birthDay, user.birthDay) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((id==null) ? 0 : id.hashCode());
        result = 31 * result + ((username==null) ? 0 : username.hashCode());
        result = 31 * result + ((firstName==null) ? 0 : firstName.hashCode());
        result = 31 * result + ((lastName==null) ? 0 : lastName.hashCode());
        result = 31 * result + ((middleName==null) ? 0 : middleName.hashCode());
        result = 31 * result + ((birthDay==null) ? 0 : birthDay.hashCode());
        result = 31 * result + ((email==null) ? 0 : email.hashCode());
        result = 31 * result + ((phone==null) ? 0 : phone.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
