package org.hotelmanager.hotelmanagementserver.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hotelmanager.hotelmanagementserver.dto.ReservationDto;
import org.hotelmanager.hotelmanagementserver.enums.ReservationStatus;

import java.time.LocalDate;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public ReservationDto getReservationDto() {
        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(id);
        reservationDto.setCheckInDate(checkInDate);
        reservationDto.setCheckOutDate(checkOutDate);
        reservationDto.setPrice(price);
        reservationDto.setStatus(status);

        reservationDto.setUserId(user.getId());
        reservationDto.setUsername(user.getUsername());

        reservationDto.setRoomId(room.getId());
        reservationDto.setRoomName(room.getName());
        reservationDto.setRoomType(room.getType());

        return reservationDto;
    }

}
