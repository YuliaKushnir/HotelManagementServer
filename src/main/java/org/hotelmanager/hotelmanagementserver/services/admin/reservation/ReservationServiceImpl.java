package org.hotelmanager.hotelmanagementserver.services.admin.reservation;

import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.ReservationResponseDto;
import org.hotelmanager.hotelmanagementserver.enums.ReservationStatus;
import org.hotelmanager.hotelmanagementserver.models.Reservation;
import org.hotelmanager.hotelmanagementserver.models.Room;
import org.hotelmanager.hotelmanagementserver.repository.ReservationRepository;
import org.hotelmanager.hotelmanagementserver.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public static final int SEARCH_RESULT_PER_PAGE = 6;

    private final RoomRepository roomRepository;

    public ReservationResponseDto getAllReservations(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);

        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setReservations(reservationPage
                .stream()
                .map(Reservation::getReservationDto)
                .collect(Collectors.toList())
        );

        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }

    public boolean changeReservationStatus(Long reservationId, String reservationStatus) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            if(Objects.equals(reservationStatus, "Approve")){
                reservation.setStatus(ReservationStatus.APPROVED);
            } else {
                reservation.setStatus(ReservationStatus.REJECTED);
            }
            reservationRepository.save(reservation);
            Room room = reservation.getRoom();
            room.setAvailable(false);
            roomRepository.save(room);

            return true;
        }
        return false;
    }

}
