package com.hostelfinder.service;

import com.hostelfinder.entity.StudentProfile;
import com.hostelfinder.entity.User;
import com.hostelfinder.repository.StudentProfileRepository;
import com.hostelfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public ProfileService(StudentProfileRepository studentProfileRepository, UserRepository userRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    // Fetch the profile, or create a new empty one if it doesn't exist yet
    public StudentProfile getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return studentProfileRepository.findByUser(user)
                .orElseGet(() -> {
                    StudentProfile newProfile = new StudentProfile();
                    newProfile.setUser(user);
                    return newProfile;
                });
    }

    // Save or update the student's preferences
    public void saveProfile(String email, String preferredCampus, Double maxPrice, String preferredRoomType) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        StudentProfile profile = studentProfileRepository.findByUser(user)
                .orElse(new StudentProfile());

        profile.setUser(user);
        profile.setPreferredCampus(preferredCampus);
        profile.setMaxPrice(maxPrice);
        profile.setPreferredRoomType(preferredRoomType);

        studentProfileRepository.save(profile);
    }
}