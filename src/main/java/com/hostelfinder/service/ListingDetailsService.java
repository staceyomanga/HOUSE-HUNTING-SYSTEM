package com.hostelfinder.service;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;
import org.springframework.stereotype.Service;

@Service
public class ListingDetailsService {

    private final HostelRepository hostelRepository;

    public ListingDetailsService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public Hostel getHostelById(Long id) {
        return hostelRepository.findById(id).orElseThrow();
    }
}