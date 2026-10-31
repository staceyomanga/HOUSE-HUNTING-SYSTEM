package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.HostelImage;

public interface Hostel_imageRepository extends JpaRepository<HostelImage, Long>{}