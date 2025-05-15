package org.hotelmanager.hotelmanagementserver.repository;

import org.hotelmanager.hotelmanagementserver.enums.UserRole;
import org.hotelmanager.hotelmanagementserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findFirstByUserRole(UserRole userRole);
}
