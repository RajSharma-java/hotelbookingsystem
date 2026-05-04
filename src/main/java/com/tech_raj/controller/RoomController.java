package com.tech_raj.controller;

import com.tech_raj.dto.RoomDto;
import com.tech_raj.response.ApiResponse;
import com.tech_raj.service.interfac.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/api/rooms/")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    public ResponseEntity<ApiResponse<RoomDto>> registerRoom(@Valid @RequestBody RoomDto roomDto){
        final RoomDto roomDto1 = roomService.registerRoom(roomDto);
        ApiResponse response= new ApiResponse(
                "Room Add succesfully!!",
                1,
                roomDto1
        );
        return ResponseEntity.ok(response);
    }
}
