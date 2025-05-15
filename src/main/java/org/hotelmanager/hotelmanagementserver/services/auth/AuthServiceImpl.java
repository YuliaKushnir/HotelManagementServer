package org.hotelmanager.hotelmanagementserver.services.auth;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.hotelmanager.hotelmanagementserver.dto.SignupRequest;
import org.hotelmanager.hotelmanagementserver.dto.UserDto;
import org.hotelmanager.hotelmanagementserver.enums.UserRole;
import org.hotelmanager.hotelmanagementserver.models.User;
import org.hotelmanager.hotelmanagementserver.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        Optional<User> admin = userRepository.findFirstByUserRole(UserRole.ADMIN);

        if(admin.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setName("admin");
            user.setUserRole(UserRole.ADMIN);

            userRepository.save(user);

            System.out.println("Admin account created");
        } else {
            System.out.println("Admin account already exists");
        }
    }

    public UserDto createUser(SignupRequest signupRequest) {
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User with this email already exists");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }

}
