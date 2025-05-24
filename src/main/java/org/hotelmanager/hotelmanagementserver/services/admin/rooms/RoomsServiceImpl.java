package org.hotelmanager.hotelmanagementserver.services.admin.rooms;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.RoomDto;
import org.hotelmanager.hotelmanagementserver.dto.RoomsResponseDto;
import org.hotelmanager.hotelmanagementserver.models.Room;
import org.hotelmanager.hotelmanagementserver.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        Pageable pageable = PageRequest.of(pageNumber, 9);
        Page<Room> rooms = roomRepository.findAll(pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setTotalPages(rooms.getTotalPages());
        roomsResponseDto.setCurrentPage(rooms.getPageable().getPageNumber());
        roomsResponseDto.setRoomDtoList(rooms.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomsResponseDto;
    }

    public RoomDto getRoomById(Long id){
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            return room.get().getRoomDto();
        } else {
            throw new EntityNotFoundException("Room with id " + id + " not found");
        }

    }

    public boolean updateRoom(Long id, RoomDto roomDto) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            Room existingRoom = room.get();

            existingRoom.setName(roomDto.getName());
            existingRoom.setType(roomDto.getType());
            existingRoom.setPrice(roomDto.getPrice());

            roomRepository.save(existingRoom);
            return true;
        }
        return false;
    }

    public void deleteRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            roomRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Room with id " + id + " not found");
        }
    }



}
