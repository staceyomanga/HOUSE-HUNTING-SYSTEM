package com.hostelfinder.controller;

import com.hostelfinder.entity.User;
import com.hostelfinder.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return "redirect:/login";
        }
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user); 
        return "profile";
    }
}
