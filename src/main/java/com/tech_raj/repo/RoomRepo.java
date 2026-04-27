package com.tech_raj.repo;

import com.tech_raj.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

    @Query("SELECT distinct r.roomType FROM Room AS r ")
    List<String>findDistinctByRoomTypes();


    @Query("SELECT r FROM Room r WHERE r.roomType LIKE %:roomTypes% AND r.id NOT IN (SELECT bk.room FROM Booking  bk WHERE " +
            "(bk.checkInDate <= :checkInDate) AND (bk.checkOutDate >= :checkOutDate))")
    List<Room>findAvailableRoomByDatesAndTypes(LocalDate checkInDate, LocalDate checkOutDate, String roomTypes);


    @Query("SELECT r FROM Room r WHERE r.id NOT IN(SELECT b.room FROM Booking b)")
    List<Room>findAllAvailableRoom();
}
