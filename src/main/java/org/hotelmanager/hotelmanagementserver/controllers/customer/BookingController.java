package org.hotelmanager.hotelmanagementserver.controllers.customer;

import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.ReservationDto;
import org.hotelmanager.hotelmanagementserver.services.customer.booking.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> postBooking(@RequestBody ReservationDto reservationDto) {
        boolean success = bookingService.postReservation(reservationDto);

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/booking/{userId}/{pageNumber}")
    public ResponseEntity<?> getAllBookingByUserId(@PathVariable Long userId, @PathVariable int pageNumber){
        try{
            return ResponseEntity.ok(bookingService.getAllReservationByUserId(userId, pageNumber));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

}
