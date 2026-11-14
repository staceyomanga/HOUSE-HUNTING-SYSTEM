package com.hostelfinder.dto;

import lombok.Data;

@Data
public class SearchRequest {
    private String location;
    private Double maxPrice;
    private String roomType;
}