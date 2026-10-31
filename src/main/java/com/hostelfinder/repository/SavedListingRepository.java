package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.SavedListings;

public interface SavedListingRepository extends JpaRepository<SavedListings, Long > {}