package com.hostelfinder.service;

import com.hostelfinder.entity.Inquiry;
import com.hostelfinder.repository.InquiryRepository;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public void saveInquiry(Inquiry inquiry) {
        inquiry.setStatus("SENT");
        inquiryRepository.save(inquiry);
    }
}