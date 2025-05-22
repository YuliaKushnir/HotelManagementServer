package org.hotelmanager.hotelmanagementserver.services.admin.rooms;

import org.hotelmanager.hotelmanagementserver.dto.RoomDto;
import org.hotelmanager.hotelmanagementserver.dto.RoomsResponseDto;

public interface RoomsService {

    boolean postRoom(RoomDto roomDto);

    RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomById(Long id);

    boolean updateRoom(Long id, RoomDto roomDto);
}
