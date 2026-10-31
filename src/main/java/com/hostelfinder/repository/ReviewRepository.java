package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{}