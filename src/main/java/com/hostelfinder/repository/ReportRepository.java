package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Report;

import java.util.List;


public interface ReportRepository extends JpaRepository <Report, Long>{

    List<Report> findByStatus(String status);
}