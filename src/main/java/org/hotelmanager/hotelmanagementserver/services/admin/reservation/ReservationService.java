package org.hotelmanager.hotelmanagementserver.services.admin.reservation;

import org.hotelmanager.hotelmanagementserver.dto.ReservationResponseDto;

public interface ReservationService {

    ReservationResponseDto getAllReservations(int pageNumber);

    boolean changeReservationStatus(Long reservationId, String reservationStatus);

}
