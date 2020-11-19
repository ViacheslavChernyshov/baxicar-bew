package com.baxicar.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RegistrationUserDto {
    @Email
    @NotNull
    @Size(min = 5, max = 256, message = "E-mail must be longer than 5 and less than 256 characters")
    private String email;

    @NotNull
    @Size(min = 8, max = 32, message = "Password length must be longer than 8 and less than 32 characters")
    private String password;

    @NotNull
    @Size(min = 8, max = 32, message = "Confirm password length must be longer than 8 and less than 32 characters")
    private String confirmPassword;

    @NotNull
    @Size(min = 1, max = 256, message = "Firstname length must be longer than 1 and less than 256 characters")
    private String firstname;

    @NotNull
    @Size(min = 1, max = 256, message = "Last name length must be longer than 1 and less than 256 characters")
    private String lastname;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@Past(message = "The specified date can not be more than the current date")
    private LocalDate dateOfBirth;

    @NotNull
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone must be longer than 10 and less than 15 numbers")
    private String phone;

    @NotNull
    @Pattern(regexp = "[MFX]", message = "Wrong gender")
    private String gender;

    @NotNull
    @AssertTrue(message = "Confirm the agreement with the conditions must be true")
    private Boolean agree;
}
