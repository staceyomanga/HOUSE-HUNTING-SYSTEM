package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Report;

public interface ReportRepository extends JpaRepository <Report, Long>{}