package org.hotelmanager.hotelmanagementserver.services.customer.booking;

import org.hotelmanager.hotelmanagementserver.dto.ReservationDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);
}
