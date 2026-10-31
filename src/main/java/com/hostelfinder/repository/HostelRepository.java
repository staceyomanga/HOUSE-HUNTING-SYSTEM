package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Hostel;

public interface HostelRepository extends JpaRepository<Hostel, Long >{}