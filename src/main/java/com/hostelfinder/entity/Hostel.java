package com.hostelfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "hostels" ) @Data

public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long hostelID;
    private String name;
    private String location;
    private String description;
    private String facilities;
    private String status = "PENDING";
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}

