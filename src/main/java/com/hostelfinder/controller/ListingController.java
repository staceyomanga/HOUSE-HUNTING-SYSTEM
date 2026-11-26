package com.hostelfinder.controller;

import com.hostelfinder.dto.ListingRequest;
import com.hostelfinder.entity.Hostel;
import com.hostelfinder.entity.Room;
import com.hostelfinder.entity.User;
import com.hostelfinder.repository.HostelRepository;
import com.hostelfinder.repository.RoomRepository;
import com.hostelfinder.service.ListingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ListingController {

    private final ListingService listingService;
    private final HostelRepository hostelRepository;
    private final RoomRepository roomRepository;

    public ListingController(ListingService listingService, HostelRepository hostelRepository, RoomRepository roomRepository) {
        this.listingService = listingService;
        this.hostelRepository = hostelRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/listing/add")
    public String showAddListingForm(HttpSession session) {
        if (!"LANDLORD".equals(session.getAttribute("role"))) {
            return "redirect:/login?error";
        }
        return "add-listing";
    }

    @PostMapping("/listing/add")
    public String saveListing(@ModelAttribute ListingRequest request,
                              @RequestParam("images") MultipartFile[] images,
                              @RequestParam(value = "singleRooms", defaultValue = "0") int singleRooms,
                              @RequestParam(value = "bedsitters", defaultValue = "0") int bedsitters,
                              @RequestParam(value = "doubleRooms", defaultValue = "0") int doubleRooms,
                              HttpSession session) {
        if (!"LANDLORD".equals(session.getAttribute("role"))) {
            return "redirect:/login?error";
        }

        Hostel hostel = new Hostel();
        hostel.setName(request.getName());
        hostel.setLocation(request.getLocation());
        hostel.setDescription(request.getDescription());
        hostel.setFacilities(request.getFacilities());
        hostel.setPricePerMonth(request.getPricePerMonth());

        Long userId = (Long) session.getAttribute("userId");
        User owner = new User();
        owner.setUserId(userId);
        hostel.setOwner(owner);

        listingService.saveListing(hostel, images, singleRooms, bedsitters, doubleRooms);

        return "redirect:/landlord/dashboard?success";
    }

    @GetMapping("/landlord/dashboard")
    public String showLandlordDashboard(HttpSession session, Model model) {
        if (!"LANDLORD".equals(session.getAttribute("role"))) {
            return "redirect:/login?error";
        }

        Long userId = (Long) session.getAttribute("userId");
        User owner = new User();
        owner.setUserId(userId);

        List<Hostel> hostels = hostelRepository.findByOwner(owner);

        // Calculate stats for EACH hostel
        for (Hostel hostel : hostels) {
            List<Room> rooms = roomRepository.findByHostel_HostelId(hostel.getHostelId());
            long available = 0, booked = 0, occupied = 0;

            for (Room r : rooms) {
                if (r.isOccupied()) occupied++;
                else if (r.isBooked()) booked++;
                else available++;
            }

            hostel.setAvailableCount(available);
            hostel.setBookedCount(booked);
            hostel.setOccupiedCount(occupied);
        }

        model.addAttribute("hostels", hostels);
        return "landlord-dashboard";
    }

    // UPDATED: Handles incremental changes (e.g., -53) with guardrails
    @PostMapping("/landlord/update-status/{id}")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam("occupiedChange") int occupiedChange,
                               @RequestParam("bookedChange") int bookedChange,
                               HttpSession session) {
        if (!"LANDLORD".equals(session.getAttribute("role"))) {
            return "redirect:/login?error";
        }

        List<Room> rooms = roomRepository.findByHostel_HostelId(id);
        if (rooms.isEmpty()) return "redirect:/landlord/dashboard?success";

        // 1. Calculate current counts
        int totalRooms = rooms.size();
        int currentOccupied = 0;
        int currentBooked = 0;

        for (Room r : rooms) {
            if (r.isOccupied()) currentOccupied++;
            else if (r.isBooked()) currentBooked++;
        }

        // 2. Apply the change
        int newOccupied = currentOccupied + occupiedChange;
        int newBooked = currentBooked + bookedChange;

        // 3. Guardrails: Prevent negative numbers or exceeding total capacity
        if (newOccupied < 0) newOccupied = 0;
        if (newBooked < 0) newBooked = 0;

        // Ensure total occupied + booked doesn't exceed total rooms
        if (newOccupied + newBooked > totalRooms) {
            int overflow = (newOccupied + newBooked) - totalRooms;
            // If it overflows, reduce the one that was increased
            if (occupiedChange > 0) newOccupied -= overflow;
            else if (bookedChange > 0) newBooked -= overflow;
        }

        // 4. Reset all rooms to available
        for (Room room : rooms) {
            room.setOccupied(false);
            room.setBooked(false);
            roomRepository.save(room);
        }

        // 5. Mark the new number of rooms as occupied and booked
        for (int i = 0; i < totalRooms; i++) {
            if (i < newOccupied) {
                rooms.get(i).setOccupied(true);
            } else if (i < newOccupied + newBooked) {
                rooms.get(i).setBooked(true);
            }
            roomRepository.save(rooms.get(i));
        }

        return "redirect:/landlord/dashboard?success";
    }
}