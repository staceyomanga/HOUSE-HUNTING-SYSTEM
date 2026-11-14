package com.hostelfinder.dto;

import lombok.Data;

@Data
public class ListingRequest {
    private String name;
    private String location;
    private String description;
    private String facilities;
    private Double pricePerMonth;
}