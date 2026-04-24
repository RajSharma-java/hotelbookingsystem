package com.tech_raj.repo;

import com.tech_raj.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

    @Query("SELECT distinct r.roomType FROM Room AS r ")
    List<String>findDistinctByRoomTypes();

    List<Room>findAllAvailableRoom();
}
