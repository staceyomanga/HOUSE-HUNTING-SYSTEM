package com.hostelfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name ="student_profiles")
@Data

public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private String preferredCampus;
    private Double maxPrice;
    private String preferredRoomType;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}