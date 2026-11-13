package com.hostelfinder.controller;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.repository.HostelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    private final HostelRepository hostelRepository;

    public AdminController(HostelRepository hostelRepository){
        this.hostelRepository =hostelRepository;
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("pendingHostels", hostelRepository.findByStatus("PENDING"));
        model.addAttribute("approvedCount", hostelRepository.findByStatus("APPROVED").size());
        return "admin-dashboard";
    }

    @GetMapping("/admin/approve/{id}")
    public String approveListing(@PathVariable Long id) {
        Hostel hostel = hostelRepository.findById(id).orElseThrow();
        hostel.setStatus("APPROVED");
        hostelRepository.save(hostel);
        return "redirect:/admin/dashboard";
    }
}