
package com.hostelfinder.controller;

import com.hostelfinder.entity.User;
import com.hostelfinder.service.AuthService;

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
    public String registerUser(
            @RequestParam("fullname") String fullName,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("role") String role,
            @RequestParam("password") String password,
            Model model) {

        try {
            authService.registerUser(
                    fullName, phone, email, role, password);

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
    public String loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session) {

        Optional<User> user =
                authService.loginUser(email, password);

        if (user.isPresent()) {
            session.setAttribute("userId",
                    user.get().getUserId());

            session.setAttribute("fullName",
                    user.get().getFullName());

            session.setAttribute("role",
                    user.get().getRole());

            return "redirect:/search";
        }

        return "redirect:/login?error";
    }

    @PostMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
