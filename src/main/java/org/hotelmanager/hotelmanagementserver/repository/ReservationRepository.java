package org.hotelmanager.hotelmanagementserver.repository;

import org.hotelmanager.hotelmanagementserver.models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findAllByUserId(Long userId, Pageable pageable);


}
