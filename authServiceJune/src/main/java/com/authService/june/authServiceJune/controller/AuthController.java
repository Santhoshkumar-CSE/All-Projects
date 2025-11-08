package com.authService.june.authServiceJune.controller;

import com.authService.june.authServiceJune.DTO.APIResponse;
import com.authService.june.authServiceJune.DTO.LoginDto;
import com.authService.june.authServiceJune.DTO.UserDTO;
import com.authService.june.authServiceJune.exception.APIException;
import com.authService.june.authServiceJune.service.AuthService;
import com.authService.june.authServiceJune.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserDTO>> registerUser(@RequestBody UserDTO userDTO)
    {
        UserDTO userDTO1=authService.register(userDTO);
        APIResponse<UserDTO> response= new APIResponse<UserDTO>("Saved",200,userDTO1);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));

    }


    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> logInCheck(@RequestBody LoginDto loginDto) {
        APIResponse<String> response;

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());

        try {
            Authentication authentication = authManager.authenticate(token);

            if (authentication.isAuthenticated()) {
               String jwtToken= jwtService.generateToken(loginDto.getUserName(),authentication.getAuthorities().iterator().next().getAuthority());
                response = new APIResponse<>("Successful", 200, jwtToken);
            } else {
                response = new APIResponse<>("Not Successful", 401, null);
            }

        } catch (AuthenticationException ex) {
            // This block is executed when username/password is invalid
            response = new APIResponse<>("Invalid username or password", 401, null);
        }

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

}
