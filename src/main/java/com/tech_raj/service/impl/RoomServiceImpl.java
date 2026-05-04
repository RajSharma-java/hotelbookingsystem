package com.tech_raj.service.impl;

import com.tech_raj.dto.RoomDto;
import com.tech_raj.dto.UserDto;
import com.tech_raj.entity.Room;
import com.tech_raj.exception.RoomAlreadyExistsException;
import com.tech_raj.exception.RoomNotFoundException;
import com.tech_raj.repo.RoomRepo;
import com.tech_raj.response.PagedResponse;
import com.tech_raj.service.interfac.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private final ModelMapper mapper;

    @Override
    public RoomDto registerRoom(RoomDto roomDto) {
        final Integer roomNo = roomRepo.findRoomNo(roomDto.getRoomNo());
        if(Objects.equals(roomNo, roomDto.getRoomNo())){
            throw new RoomAlreadyExistsException("Room No already exists, Please check the roomNo");
        }
        final Room room = mapper.map(roomDto, Room.class);
        final Room save = roomRepo.save(room);
        return mapper.map(save, RoomDto.class);
    }

    @Override
    public PagedResponse<RoomDto> getAllRoom(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        final Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
         Page<Room> pages = roomRepo.findAll(pageable);
        final List<RoomDto> data = pages.stream().map((element) -> mapper.map(element, RoomDto.class)).collect(Collectors.toList());
        return new PagedResponse<>(
                data,
                pages.getNumber(),
                pages.getSize(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.isLast()
        );
    }

    @Override
    public RoomDto getRoomById(Long id) {
        final Room room = roomRepo.findById(id).orElseThrow(() -> new RoomNotFoundException("Room Not found check id again!!"));
         return mapper.map(room,RoomDto.class);
    }

    @Override
    public RoomDto updateRoomById(Long id, UserDto dto) {
        return null;
    }

    @Override
    public void deleteRoom(Long id) {
    }

    @Override
    public void deleteRoomByRoomNo(Integer roomNo) {

    }
}
