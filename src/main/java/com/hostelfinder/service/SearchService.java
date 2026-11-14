package com.hostelfinder.service;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private HostelRepository hostelRepository;

    public List<Hostel> search(String location) {
        if (location != null && !location.isEmpty()) {
            return hostelRepository.findByStatusAndLocationContainingIgnoreCase("APPROVED", location);
        } else {
            return hostelRepository.findByStatus("APPROVED");
        }
    }
}