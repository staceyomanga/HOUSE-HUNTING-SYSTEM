package com.hostelfinder.controller;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ListingController {

    private final HostelRepository hostelRepository;

    public ListingController(HostelRepository hostelRepository){
        this.hostelRepository =     hostelRepository;
    }

    @GetMapping("/listing/add")
    public String showAddListingForm() { 
        return "add-listing"; 
    }

    @PostMapping("/listing/add")
    public String saveListing(Hostel hostel) {
        hostel.setStatus("PENDING"); // FR-11
        hostelRepository.save(hostel);
        return "redirect:/landlord/dashboard?success";
    }
}