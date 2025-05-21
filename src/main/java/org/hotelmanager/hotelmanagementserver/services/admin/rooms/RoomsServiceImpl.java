package org.hotelmanager.hotelmanagementserver.services.admin.rooms;

import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.RoomDto;
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
public class RoomsServiceImpl implements RoomsService {

    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto) {
        try{
            Room room = new Room();

            room.setName(roomDto.getName());
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());
            room.setAvailable(roomDto.isAvailable());
            roomRepository.save(room);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public RoomsResponseDto getAllRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Room> rooms = roomRepository.findAll(pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setTotalPages(rooms.getTotalPages());
        roomsResponseDto.setCurrentPage(rooms.getPageable().getPageNumber());
        roomsResponseDto.setRoomDtoList(rooms.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomsResponseDto;
    }



}
