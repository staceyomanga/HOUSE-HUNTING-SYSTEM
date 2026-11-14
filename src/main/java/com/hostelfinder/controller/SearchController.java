package com.hostelfinder.controller;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final HostelRepository hostelRepository;

    public SearchController(HostelRepository hostelRepository){
        this.hostelRepository = hostelRepository;
    }

    @GetMapping("/search")
    public String searchHostels(@RequestParam(required = false) String location, Model model) {
        List<Hostel> results;
        
        if (location != null && !location.isEmpty()) {
            results = hostelRepository.findByStatusAndLocationContainingIgnoreCase("APPROVED", location);
        } else {
            results = hostelRepository.findByStatus("APPROVED");
        }
        
        model.addAttribute("hostels", results);
        model.addAttribute("totalCount", results.size()); 
        
        return "search";
    }
}