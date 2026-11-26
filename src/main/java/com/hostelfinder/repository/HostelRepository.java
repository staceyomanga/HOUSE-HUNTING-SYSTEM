package com.hostelfinder.repository;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface HostelRepository extends JpaRepository<Hostel, Long> {
    List<Hostel> findByStatus(String status);
    List<Hostel> findByOwner(User owner);
    List<Hostel> findByStatusAndLocationContainingIgnoreCase(String status, String location);

    // NEW: Multi-filter search query
    @Query("SELECT h FROM Hostel h WHERE h.status = 'APPROVED' " +
            "AND (:location IS NULL OR LOWER(h.location) LIKE LOWER(CONCAT('%', :location, '%'))) " +
            "AND (:maxPrice IS NULL OR h.pricePerMonth <= :maxPrice) " +
            "AND (:roomType IS NULL OR h.id IN (SELECT r.hostel.hostelId FROM Room r WHERE r.roomType = :roomType))")
    List<Hostel> searchHostels(@Param("location") String location,
                               @Param("maxPrice") Double maxPrice,
                               @Param("roomType") String roomType);
}