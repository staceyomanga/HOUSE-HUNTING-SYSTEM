package com.hostelfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelfinder.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long >{}