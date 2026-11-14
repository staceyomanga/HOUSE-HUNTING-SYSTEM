package com.hostelfinder.service;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private HostelRepository hostelRepository;

    public List<Hostel> getPendingHostels() {
        return hostelRepository.findByStatus("PENDING");
    }

    public long getApprovedCount() {
        return hostelRepository.findByStatus("APPROVED").size();
    }

    public void approveHostel(Long id) {
        Hostel hostel = hostelRepository.findById(id).orElseThrow();
        hostel.setStatus("APPROVED");
        hostelRepository.save(hostel);
    }
}