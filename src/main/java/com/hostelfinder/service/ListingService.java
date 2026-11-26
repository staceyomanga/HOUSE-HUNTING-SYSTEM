package com.hostelfinder.service;

import com.hostelfinder.entity.Hostel;
import com.hostelfinder.entity.HostelImage;
import com.hostelfinder.entity.Room;
import com.hostelfinder.repository.HostelImageRepository;
import com.hostelfinder.repository.HostelRepository;
import com.hostelfinder.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ListingService {

    private final HostelRepository hostelRepository;
    private final HostelImageRepository hostelImageRepository;
    private final RoomRepository roomRepository;

    public ListingService(HostelRepository hostelRepository, HostelImageRepository hostelImageRepository, RoomRepository roomRepository) {
        this.hostelRepository = hostelRepository;
        this.hostelImageRepository = hostelImageRepository;
        this.roomRepository = roomRepository;
    }

    public void saveListing(Hostel hostel, MultipartFile[] images, int singleRooms, int bedsitters, int doubleRooms) {
        hostel.setStatus("PENDING");
        Hostel savedHostel = hostelRepository.save(hostel);

        // Create Rooms based on input
        for (int i = 0; i < singleRooms; i++) {
            Room r = new Room(); r.setRoomType("Single"); r.setHostel(savedHostel); roomRepository.save(r);
        }
        for (int i = 0; i < bedsitters; i++) {
            Room r = new Room(); r.setRoomType("Bedsitter"); r.setHostel(savedHostel); roomRepository.save(r);
        }
        for (int i = 0; i < doubleRooms; i++) {
            Room r = new Room(); r.setRoomType("Double"); r.setHostel(savedHostel); roomRepository.save(r);
        }

        // Handle Images
        if (images != null && images.length > 0) {
            try {
                Path uploadPath = Paths.get("uploads");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                for (MultipartFile file : images) {
                    if (!file.isEmpty()) {
                        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                        Path filePath = uploadPath.resolve(fileName);
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        HostelImage image = new HostelImage();
                        image.setImagePath(fileName);
                        image.setHostel(savedHostel);
                        hostelImageRepository.save(image);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }
    }
}