package com.hostelfinder.controller;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.entity.HostelImage;
import com.hostelfinder.repository.HostelRepository;
import com.hostelfinder.repository.HostelImageRepository;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ListingDetailsController {


    private final HostelRepository hostelRepository;

    private final HostelImageRepository hostelImageRepository;

    //Constructor
    public ListingDetailsController(HostelRepository hostelRepository, HostelImageRepository hostelImageRepository){
        this.hostelRepository = hostelRepository;
        this.hostelImageRepository = hostelImageRepository;
    }

    @GetMapping("/listing/{id}")
    public String showListingDetails(@PathVariable Long id, Model model){

        //fetch hostel by ID
        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Hostel not found"));

        //fetch images for the hostel
        List<HostelImage> images = hostelImageRepository.findByHostel_HostelId(id);

            model.addAttribute("hostel", hostel);
            model.addAttribute("images",images);

            //return the HTML template
            return "listing-details";
    }
}