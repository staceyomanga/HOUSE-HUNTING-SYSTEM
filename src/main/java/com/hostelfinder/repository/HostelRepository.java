package com.hostelfinder.repository;

import com.hostelfinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Hostel;
import java.util.List;

import java.util.List;

public interface HostelRepository extends JpaRepository<Hostel, Long >{
    List<Hostel> findByStatus(String status);

    List<Hostel> findByOwner(User owner);

    List<Hostel> findByStatusAndLocationContainingIgnoreCase(String status, String location);

    List<Hostel> findByStatusAndPricePerMonthLessThanEqual(String status, Double maxPrice);

    List<Hostel> findByStatusAndLocationContainingIgnoreCaseAndPricePerMonthLessThanEqual(String status, String location, Double maxPrice);
}