package com.baxicar.service;

import com.baxicar.dto.RegistrationUserDto;
import com.baxicar.model.Role;
import com.baxicar.model.Status;
import com.baxicar.model.User;
import com.baxicar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        User registeredUser = userRepository.save(user);
        registeredUser.setPassword("");
        return registeredUser;
    }

//    @Override
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    @Override
    public User findByEmailAndActiveTrue(String email) {
        return userRepository.findByEmailAndActiveTrue(email);
    }

    @Override
    public Boolean isPasswordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @Override
    public User convertUserDtoToUser(RegistrationUserDto userDto) {
        System.out.println(userDto);
        return new User().builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .dateOfBirth(userDto.getDateOfBirth())
                .gender(userDto.getGender())
                .role(Role.USER)
                .status(Status.ACTIVE)
                .active(true)
                .build();
    }
}
