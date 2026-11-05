package com.hostelfinder.controller;

import com.hostelfinder.entity.Review;
import com.hostelfinder.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.steroetype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/review/submit")
    public String submitReview(Review review) {
        reviewRepository.save(review);
        return "redirect:/listing/" + review.getHostel().getHostelId() + "?reviewed";
    }
}