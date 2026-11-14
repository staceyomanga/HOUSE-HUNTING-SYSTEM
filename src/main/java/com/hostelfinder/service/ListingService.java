package com.hostelfinder.service;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;
import org.springframework.stereotype.Service;

@Service
public class ListingService {

    private final HostelRepository hostelRepository;

    public ListingService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public void saveListing(Hostel hostel) {
        hostel.setStatus("PENDING");
        hostelRepository.save(hostel);
    }
}