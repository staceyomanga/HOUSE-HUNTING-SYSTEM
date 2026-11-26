package com.hostelfinder.controller;

import com.hostelfinder.entity.Booking;
import com.hostelfinder.entity.Hostel;
import com.hostelfinder.entity.HostelImage;
import com.hostelfinder.entity.Review;
import com.hostelfinder.entity.SavedListing;
import com.hostelfinder.entity.User;
import com.hostelfinder.repository.HostelImageRepository;
import com.hostelfinder.repository.HostelRepository;
import com.hostelfinder.repository.ReviewRepository;
import com.hostelfinder.repository.SavedListingRepository;
import com.hostelfinder.repository.UserRepository;
import com.hostelfinder.service.BookingService;
import com.hostelfinder.service.ListingDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class ListingDetailsController {

    private final ListingDetailsService listingDetailsService;
    private final HostelImageRepository hostelImageRepository;
    private final BookingService bookingService;
    private final SavedListingRepository savedListingRepository;
    private final UserRepository userRepository;
    private final HostelRepository hostelRepository;
    private final ReviewRepository reviewRepository;

    public ListingDetailsController(ListingDetailsService listingDetailsService,
                                    HostelImageRepository hostelImageRepository,
                                    BookingService bookingService,
                                    SavedListingRepository savedListingRepository,
                                    UserRepository userRepository,
                                    HostelRepository hostelRepository,
                                    ReviewRepository reviewRepository) {
        this.listingDetailsService = listingDetailsService;
        this.hostelImageRepository = hostelImageRepository;
        this.bookingService = bookingService;
        this.savedListingRepository = savedListingRepository;
        this.userRepository = userRepository;
        this.hostelRepository = hostelRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/listing/{id}")
    public String showListingDetails(@PathVariable Long id, Model model) {
        Hostel hostel = listingDetailsService.getHostelById(id);
        List<HostelImage> images = hostelImageRepository.findByHostel_HostelId(id);
        List<Review> reviews = reviewRepository.findByHostel_HostelIdOrderByCreatedAtDesc(id);

        model.addAttribute("hostel", hostel);
        model.addAttribute("images", images);
        model.addAttribute("reviews", reviews);
        return "listing-details";
    }

    @PostMapping("/booking/request")
    public String requestBooking(@ModelAttribute Booking booking, HttpSession session) {
        if (!"STUDENT".equals(session.getAttribute("role"))) {
            return "redirect:/login?error";
        }
        bookingService.createBooking(booking);
        return "redirect:/listing/" + booking.getRoom().getHostel().getHostelId() + "?status=booked";
    }

    @PostMapping("/listing/save/{id}")
    public String saveListing(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null || !"STUDENT".equals(role)) {
            return "redirect:/login?error";
        }

        User user = userRepository.findById(userId).orElseThrow();
        Hostel hostel = hostelRepository.findById(id).orElseThrow();

        if (!savedListingRepository.existsByUserAndHostel(user, hostel)) {
            SavedListing savedListing = new SavedListing();
            savedListing.setUser(user);
            savedListing.setHostel(hostel);
            savedListingRepository.save(savedListing);
        }

        return "redirect:/listing/" + id + "?status=saved";
    }
}