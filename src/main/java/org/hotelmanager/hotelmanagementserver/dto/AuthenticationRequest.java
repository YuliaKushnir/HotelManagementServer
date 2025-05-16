package org.hotelmanager.hotelmanagementserver.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;

}
