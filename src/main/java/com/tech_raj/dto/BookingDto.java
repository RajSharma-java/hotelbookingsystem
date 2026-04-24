package com.tech_raj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech_raj.entity.Room;
import com.tech_raj.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    private LocalDate checkInDate;

    @Future(message = "check out date must be in the future!!")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "Number of adults must not be less that 1 ")
    private Integer numOfAdults;
    private Integer numOfChildren;
    private Integer totalNumberOfGuest;
    private String bookingConfirmationCode;
    private UserDto user;
    private RoomDto room;

}
