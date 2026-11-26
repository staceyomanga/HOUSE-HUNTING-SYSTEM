package com.hostelfinder.controller;

import com.hostelfinder.entity.Review;
import com.hostelfinder.entity.User;
import com.hostelfinder.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ReviewController {

    private  final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/review/submit")
    public String submitReview(@ModelAttribute Review review, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        // NFR-03: Only logged-in STUDENTS can submit reviews
        if (userId == null || !"STUDENT".equals(role)) {
            return "redirect:/login?error"; // Blocks Landlords and Guests
        }

        // Link the review to the logged-in student
        User student = new User();
        student.setUserId(userId);
        review.setStudent(student);

        reviewService.saveReview(review);
        return "redirect:/listing/" + review.getHostel().getHostelId() + "?reviewed";
    }
}