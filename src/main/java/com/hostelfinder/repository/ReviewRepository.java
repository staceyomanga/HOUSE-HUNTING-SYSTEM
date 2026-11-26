package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<Review> findByHostel_HostelIdOrderByCreatedAtDesc(Long hostelId);
}