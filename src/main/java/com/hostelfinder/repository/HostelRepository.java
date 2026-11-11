package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Hostel;
import java.util.List;

public interface HostelRepository extends JpaRepository<Hostel, Long> {

    List<Hostel> findByStatus(String status);

    List<Hostel> findByStatusAndLocationContainingIgnoreCase(
            String status, String location);
}