package org.hotelmanager.hotelmanagementserver.services.customer.booking;

import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.ReservationDto;
import org.hotelmanager.hotelmanagementserver.enums.ReservationStatus;
import org.hotelmanager.hotelmanagementserver.models.Reservation;
import org.hotelmanager.hotelmanagementserver.models.Room;
import org.hotelmanager.hotelmanagementserver.models.User;
import org.hotelmanager.hotelmanagementserver.repository.ReservationRepository;
import org.hotelmanager.hotelmanagementserver.repository.RoomRepository;
import org.hotelmanager.hotelmanagementserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    public boolean postReservation(ReservationDto reservationDto){
        Optional<User> user = userRepository.findById(reservationDto.getUserId());
        Optional<Room> room = roomRepository.findById(reservationDto.getRoomId());

        if(user.isPresent() && room.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setUser(user.get());
            reservation.setRoom(room.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(days * room.get().getPrice());

            reservationRepository.save(reservation);
            return true;
        }

        return false;
    }

}
