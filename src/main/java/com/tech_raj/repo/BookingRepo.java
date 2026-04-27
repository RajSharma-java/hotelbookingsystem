package com.tech_raj.repo;

import com.tech_raj.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking>findByRoomId(Long id);

    List<Booking>findByBookingConfirmationCode(String confirmationCode);

    List<Booking>findByUserId(Long id);
}
