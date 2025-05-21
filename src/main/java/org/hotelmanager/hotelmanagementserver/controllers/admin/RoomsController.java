package org.hotelmanager.hotelmanagementserver.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.RoomDto;
import org.hotelmanager.hotelmanagementserver.services.admin.rooms.RoomsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomsService roomsService;

    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto) {
        boolean success = roomsService.postRoom(roomDto);

        if(success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAllRooms(@PathVariable int pageNumber) {
        return ResponseEntity.ok(roomsService.getAllRooms(pageNumber));
    }

}
