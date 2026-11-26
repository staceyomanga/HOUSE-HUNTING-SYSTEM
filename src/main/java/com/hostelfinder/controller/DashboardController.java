package com.hostelfinder.controller;

import com.hostelfinder.entity.StudentProfile;
import com.hostelfinder.entity.User;
import com.hostelfinder.repository.StudentProfileRepository;
import com.hostelfinder.repository.UserRepository;
import com.hostelfinder.repository.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final InquiryRepository inquiryRepository;
    private final SavedListingRepository savedListingRepository;
    private final StudentProfileRepository studentProfileRepository;

    public DashboardController(UserRepository userRepository,
                               BookingRepository bookingRepository,
                               InquiryRepository inquiryRepository,
                               SavedListingRepository savedListingRepository, StudentProfileRepository studentProfileRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.inquiryRepository = inquiryRepository;
        this.savedListingRepository = savedListingRepository;
        this.studentProfileRepository = studentProfileRepository;
    }
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        // NFR-03: Security check
        if (userId == null && role == null) {
            return "redirect:/login?error";
        }

        User user = userRepository.findById(userId).orElseThrow();

        // Route based on Role using if/else
        if (role.equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (role.equals("LANDLORD")) {
            return "redirect:/landlord/dashboard";
        } else {
            // Default to Student Dashboard
            model.addAttribute("bookings", bookingRepository.findByUser_UserId(userId));
            model.addAttribute("inquiries", inquiryRepository.findByStudent_UserId(userId));
            model.addAttribute("savedListings", savedListingRepository.findByUser(user));

            StudentProfile profile = studentProfileRepository.findByUser(user).orElse(new StudentProfile());
            model.addAttribute("studentProfile", profile);

            return "student-dashboard";
        }
    }

   }