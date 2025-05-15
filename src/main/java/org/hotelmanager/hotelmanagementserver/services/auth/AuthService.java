package org.hotelmanager.hotelmanagementserver.services.auth;

import org.hotelmanager.hotelmanagementserver.dto.SignupRequest;
import org.hotelmanager.hotelmanagementserver.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

}
