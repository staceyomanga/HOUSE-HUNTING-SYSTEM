package com.hostelfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "hostel_images")
@Data

public class HostelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long imageId;

    private String imagePath;
    private String caption;

    @ManyToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
}