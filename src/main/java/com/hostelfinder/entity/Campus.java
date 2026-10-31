package com.hostelfinder.entity;

import jakarta.persistence.*;
import  lombok.Data;

@Entity @Table(name = "campuses")
@Data

public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campusId;

    private String name;
    private Double latitude;
    private Double longitude;
}
