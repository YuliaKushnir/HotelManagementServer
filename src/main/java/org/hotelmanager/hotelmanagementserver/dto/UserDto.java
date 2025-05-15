package org.hotelmanager.hotelmanagementserver.dto;

import lombok.Data;
import org.hotelmanager.hotelmanagementserver.enums.UserRole;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
