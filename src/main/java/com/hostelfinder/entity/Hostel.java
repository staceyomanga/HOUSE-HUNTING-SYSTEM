package com.hostelfinder.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hostels")
@Data
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostelId;

    private String name;
    private String location;
    private String description;
    private String facilities;
    private Double pricePerMonth;
    private String status = "PENDING";
    private String rejectionReason;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<HostelImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @Transient
    private long availableCount;

    @Transient
    private long bookedCount;

    @Transient
    private long occupiedCount;
}