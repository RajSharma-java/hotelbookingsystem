package com.tech_raj.service.interfac;

import com.tech_raj.dto.RoomDto;
import com.tech_raj.dto.UserDto;
import com.tech_raj.response.PagedResponse;

public interface RoomService {

    RoomDto registerRoom(RoomDto roomDto);

    PagedResponse<RoomDto> getAllRoom(int pageNumber, int pageSize, String sortBy, String sortDir);

    RoomDto getRoomById(Long id);

    RoomDto updateRoomById(Long id, UserDto dto);

    void deleteRoom(Long id);

    void deleteRoomByRoomNo(Integer roomNo);

}
