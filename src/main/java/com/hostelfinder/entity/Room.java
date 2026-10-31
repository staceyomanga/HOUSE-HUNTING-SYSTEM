package com.hostelfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "rooms")
@Data

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomType;
    private Double pricePerMonth;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
}