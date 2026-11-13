package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.HostelImage;

import java.util.List;

public interface HostelImageRepository extends JpaRepository<HostelImage, Long>{
    List<HostelImage>findByHostel_HostelId(Long hostelId);
}