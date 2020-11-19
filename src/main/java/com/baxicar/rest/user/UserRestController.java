package com.baxicar.rest.user;

import com.baxicar.dto.RegistrationUserDto;
import com.baxicar.model.User;
import com.baxicar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/signup")
    // @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Object> addDriverRoute(@Valid @RequestBody RegistrationUserDto userDto, Errors errors) {
        Map<Object, Object> response = new HashMap<>();

        if (userDto != null && errors.hasErrors()) {
            String errorsDto = errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(" | "));
            response.put("errorsDto", errorsDto);
            response.put("userDto", userDto);
        } else {
            User dbUser = userService.findByEmailAndActiveTrue(userDto.getEmail());
            if (dbUser == null) {
                if (userDto.getPassword() != null && userDto.getConfirmPassword() != null && userService.isPasswordMatch(userDto.getPassword(), userDto.getConfirmPassword())) {
                    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    User user = userService.convertUserDtoToUser(userDto);
                    User registeredUser = userService.create(user);
                    response.put("user", registeredUser);
                    return ResponseEntity.ok(registeredUser);
                } else {
                    response.put("PasswordsDoesntMatch", "The passwords do not match");
                }
            } else {
                response.put("EmailAlreadyRegistered", "This email address has already been registered");
            }
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @GetMapping
//    public List<User> getAll() {
//        return DEVELOPERS;
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('developers:read')")
//    public User getById(@PathVariable Long id) {
//        return DEVELOPERS.stream().filter(developer -> developer.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    @PreAuthorize("hasAuthority('developers:write')")
//    public User create(@RequestBody Developer developer) {
//        this.DEVELOPERS.add(developer);
//        return developer;
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public void deleteById(@PathVariable Long id) {
//        this.DEVELOPERS.removeIf(developer -> developer.getId().equals(id));
//    }


}
