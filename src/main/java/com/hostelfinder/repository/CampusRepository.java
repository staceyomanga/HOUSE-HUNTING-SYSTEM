package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long>{}