package com.hostelfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "saved_listings")
@Data

public class SavedListings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savedId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
}