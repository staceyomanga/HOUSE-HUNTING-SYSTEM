package com.hostelfinder.repository;

import com.hostelfinder.entity.StudentProfile;
import com.hostelfinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long>{

    Optional<StudentProfile> findByUser(User user);
}