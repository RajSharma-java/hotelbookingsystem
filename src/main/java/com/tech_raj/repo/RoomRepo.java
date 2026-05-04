package com.tech_raj.repo;

import com.tech_raj.dto.RoomDto;
import com.tech_raj.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

    @Query("SELECT distinct r.roomType FROM Room AS r ")
    List<String>findDistinctByRoomTypes();


    @Query("""
            SELECT r FROM Room r 
            WHERE r.roomType LIKE %:roomTypes%
            AND r.id NOT IN (
                SELECT bk.room.id FROM Booking bk 
                WHERE (bk.checkInDate <= :checkOutDate) 
                AND (bk.checkOutDate >= :checkInDate)
            )
            """)
    List<Room> findAvailableRoomByDatesAndTypes(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            String roomTypes
    );


    @Query("""
    SELECT r FROM Room r
    LEFT JOIN Booking b ON b.room = r
    WHERE b.id IS NULL
""")
    List<Room>findAllAvailableRoom();


    @Modifying
    @Transactional
    @Query("DELETE FROM Room r WHERE r.roomNo = :roomNo")
    void deleteRoomByRoomNo(@Param("roomNo") Integer roomNo);

    @Query("SELECT a.roomNo FROM Room a WHERE a.roomNo = :roomNo")
    Integer findRoomNo(@Param("roomNo") Integer roomNo);


}
