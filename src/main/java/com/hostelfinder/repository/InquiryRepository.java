package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>{}