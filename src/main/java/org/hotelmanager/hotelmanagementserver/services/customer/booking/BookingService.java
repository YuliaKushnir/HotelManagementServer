package org.hotelmanager.hotelmanagementserver.services.customer.booking;

import org.hotelmanager.hotelmanagementserver.dto.ReservationDto;
import org.hotelmanager.hotelmanagementserver.dto.ReservationResponseDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);

    ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber);
}
