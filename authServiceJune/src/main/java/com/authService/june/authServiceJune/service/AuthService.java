package com.authService.june.authServiceJune.service;

import com.authService.june.authServiceJune.DTO.UserDTO;
import com.authService.june.authServiceJune.entity.User;
import com.authService.june.authServiceJune.exception.APIException;
import com.authService.june.authServiceJune.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO register(UserDTO dto) {

        if(userRepository.existsByUserName(dto.getUserName()))
        {
            throw new APIException("Username "+dto.getUserName()+" already exits");
        }
        else if(userRepository.existsByEmail(dto.getEmail()))
        {
            throw new APIException("Email "+dto.getEmail()+" already exits");
        }
        User user=modelMapper.map(dto,User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saveUser=userRepository.save(user);
        return modelMapper.map(saveUser,UserDTO.class);
    }
}
