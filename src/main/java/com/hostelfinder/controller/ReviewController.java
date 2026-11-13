package com.hostelfinder.controller;

import com.hostelfinder.entity.Review;
import com.hostelfinder.repository.ReviewRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    private  final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @PostMapping("/review/submit")
    public String submitReview(Review review) {
        reviewRepository.save(review);
        return "redirect:/listing/" + review.getHostel().getHostelId() + "?status=reviewed";
    }
}