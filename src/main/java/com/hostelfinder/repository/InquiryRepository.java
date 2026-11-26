package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Inquiry;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>{

    List<Inquiry> findByHostel_Owner_UserIdOrderByCreatedAtDesc(Long ownerId);
    List<Inquiry> findByStudent_UserId(Long studentId);

}