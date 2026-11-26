package com.hostelfinder.service;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchService {

    private final HostelRepository hostelRepository;

    public SearchService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public List<Hostel> search(String location, Double maxPrice, String roomType) {
        // Clean up empty strings to null so the query ignores them
        String cleanLocation = (location != null && !location.isEmpty()) ? location : null;
        String cleanRoomType = (roomType != null && !roomType.isEmpty()) ? roomType : null;

        return hostelRepository.searchHostels(cleanLocation, maxPrice, cleanRoomType);
    }
}