package com.hostelfinder.controller;

import com.hostelfinder.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public String showBookings(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login?error";
        }
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings"; // You will need a bookings.html template
    }
}