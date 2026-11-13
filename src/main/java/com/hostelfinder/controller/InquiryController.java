package com.hostelfinder.controller;

import com.hostelfinder.entity.Inquiry;
import com.hostelfinder.repository.InquiryRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class InquiryController {
    private final InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository){
        this.inquiryRepository = inquiryRepository;
    }

    @PostMapping("/inquiry/send")
    public String sendInquiry(@ModelAttribute Inquiry inquiry){
        inquiry.setStatus("SENT");
        inquiryRepository.save(inquiry);
        return "redirect:/listing/" + inquiry.getHostel().getHostelId() + "?sent";
    }
}