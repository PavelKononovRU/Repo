package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity

@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="position")
    private String position;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name="jobs_address", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name= "address_id"))
    private List<Address> address;

    @OneToOne(mappedBy = "job")
    private User user;

    public Job() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id) && Objects.equals(title, job.title) && Objects.equals(position, job.position);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((id==null) ? 0 : id.hashCode());
        result = 31 * result + ((title==null) ? 0 : title.hashCode());
        result = 31 * result + ((position==null) ? 0 : position.hashCode());
        return result;    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", position='" + position + '\'' +
                ", user=" + user +
                '}';
    }
}
