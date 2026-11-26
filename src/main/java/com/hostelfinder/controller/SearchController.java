package com.hostelfinder.controller;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String searchHostels(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String roomType,
            Model model) {

        List<Hostel> results = searchService.search(location, maxPrice, roomType);
        model.addAttribute("hostels", results);
        model.addAttribute("totalCount", results.size());

        // Keep the search values in the form after submission
        model.addAttribute("location", location);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("roomType", roomType);

        return "search";
    }
}