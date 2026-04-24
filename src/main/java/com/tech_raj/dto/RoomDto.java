package com.tech_raj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech_raj.entity.Booking;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
    private Long id;

    @NotBlank(message = "Room type should be required!!")
    private String roomType;

    private BigDecimal roomPrice;

    private String photoUrl;

    private String description;
    private List<BookingDto> booking= new ArrayList<>();
}
