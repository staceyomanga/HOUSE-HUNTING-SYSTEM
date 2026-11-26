package com.hostelfinder.controller;

import com.hostelfinder.entity.User;
import com.hostelfinder.service.AuthService;
import com.hostelfinder.dto.RegisterRequest;
import com.hostelfinder.dto.LoginRequest;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterRequest request, Model model) {

        try {
            authService.registerUser(
                    request.getFullName(),
                    request.getPhone(),
                    request.getEmail(),
                    request.getRole(),
                    request.getPassword()
            );
            return "redirect:/login?registered";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginRequest request, HttpSession session){

        Optional<User> user = authService.loginUser(request.getEmail(), request.getPassword());

        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getUserId());
            session.setAttribute("role", user.get().getRole());
            session.setAttribute("fullName", user.get().getFullName());
            session.setAttribute("email", user.get().getEmail());
            return "redirect:/search";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
