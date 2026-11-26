package com.hostelfinder.repository;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.entity.SavedListing;
import com.hostelfinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavedListingRepository extends JpaRepository<SavedListing, Long> {
    List<SavedListing> findByUser(User user);
    boolean existsByUserAndHostel(User user, Hostel hostel);
}