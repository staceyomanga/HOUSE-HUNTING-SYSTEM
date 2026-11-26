package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>{
    List<Booking> findByUser_UserId(Long userId);
}