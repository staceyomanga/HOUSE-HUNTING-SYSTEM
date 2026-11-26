package com.hostelfinder.controller;

import com.hostelfinder.entity.StudentProfile;
import com.hostelfinder.service.ProfileService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null){
            return "redirect:/login?error";
        }

        StudentProfile profile = profileService.getProfile(email);
        model.addAttribute("profile", profile);
        return "profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@RequestParam("campus") String campus,
                              @RequestParam("maxPrice") Double maxPrice,
                              @RequestParam("roomType") String roomType,
                              HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login?error";
        }

        profileService.saveProfile(email, campus, maxPrice, roomType);
        return "redirect:/profile?success";
    }
}

