package org.hotelmanager.hotelmanagementserver.dto;

import lombok.Data;
import org.hotelmanager.hotelmanagementserver.enums.UserRole;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private UserRole userRole;
}
