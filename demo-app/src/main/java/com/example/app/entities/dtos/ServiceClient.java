package com.example.app.entities.dtos;

import com.example.app.validation.FieldMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class ServiceClient {
    @NotNull(message = "phone number is required")
    @Size(min = 4, message = "phone number length must be 4 characters")
    private String phone;

    @NotNull(message = "is required")
    @Size(min = 4, message = "password is too short")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 4, message = "password is too short")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 2, message = "is required")
    private String name;
}
