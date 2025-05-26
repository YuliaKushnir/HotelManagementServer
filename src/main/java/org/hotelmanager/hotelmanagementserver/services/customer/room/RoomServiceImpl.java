package org.hotelmanager.hotelmanagementserver.services.customer.room;

import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.RoomsResponseDto;
import org.hotelmanager.hotelmanagementserver.models.Room;
import org.hotelmanager.hotelmanagementserver.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomsResponseDto getAvailableRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 9);
        Page<Room> rooms = roomRepository.findByAvailable(true, pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setTotalPages(rooms.getTotalPages());
        roomsResponseDto.setCurrentPage(rooms.getPageable().getPageNumber());
        roomsResponseDto.setRoomDtoList(rooms.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomsResponseDto;
    }


}
