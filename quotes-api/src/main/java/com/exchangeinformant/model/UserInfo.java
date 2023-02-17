package com.exchangeinformant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="counter")
    private int counter;
    @Column(name="last_visit")
    private LocalDateTime lastVisit;
    @Column(name="last_request")
    private LocalDateTime lastRequest;
    @Column(name="source")
    private String source;
}
