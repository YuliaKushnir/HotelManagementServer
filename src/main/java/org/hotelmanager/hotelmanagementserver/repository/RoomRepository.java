package org.hotelmanager.hotelmanagementserver.repository;

import org.hotelmanager.hotelmanagementserver.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


}
