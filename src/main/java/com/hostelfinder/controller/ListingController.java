package com.hostelfinder.controller;

/*import libraries */
import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ListingController {

    @Autowired
    private HostelRepository hostelRepository;

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