package com.authService.june.authServiceJune.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    private String userName;

    private String email;

    private String password;

    private String role;
}
