package com.hostelfinder.controller;

import com.hostelfinder.entity.Inquiry;
import com.hostelfinder.entity.User;
import com.hostelfinder.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/inquiry/new/{id}")
    public String showInquiryForm(@PathVariable Long id, Model model, HttpSession session) {
        // NFR-03 + Scope: Only STUDENTS can send inquiries
        if (!"STUDENT".equals(session.getAttribute("role"))) {
            return "redirect:/login?error";
        }
        model.addAttribute("hostelId", id);
        return "inquiry-form";
    }

    @PostMapping("/inquiry/send")
    public String sendInquiry(@ModelAttribute Inquiry inquiry, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        // NFR-03 + Scope: Only STUDENTS can send inquiries
        if (userId == null || !"STUDENT".equals(role)) {
            return "redirect:/login?error";
        }

        User student = new User();
        student.setUserId(userId);
        inquiry.setStudent(student);

        inquiryService.saveInquiry(inquiry);
        return "redirect:/listing/" + inquiry.getHostel().getHostelId() + "?sent";
    }
}