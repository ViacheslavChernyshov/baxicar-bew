package com.baxicar.service;

import com.baxicar.dto.RegistrationUserDto;
import com.baxicar.model.User;

public interface UserService {
    User create(User user);

    User save(User user);

    User findByUserId(Long userId);

    User findByEmailAndActiveTrue(String email);

    Boolean isPasswordMatch(String password, String confirmPassword);

    User convertUserDtoToUser(RegistrationUserDto userDto);
}
