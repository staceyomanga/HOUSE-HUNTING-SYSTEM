package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
}