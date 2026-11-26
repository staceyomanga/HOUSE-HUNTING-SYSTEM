package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long >{
    List<Room> findByHostel_HostelId(Long hostelId);
}