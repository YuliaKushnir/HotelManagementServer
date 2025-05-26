package org.hotelmanager.hotelmanagementserver.services.customer.room;


import org.hotelmanager.hotelmanagementserver.dto.RoomsResponseDto;

public interface RoomService {

    RoomsResponseDto getAvailableRooms(int pageNumber);

}
