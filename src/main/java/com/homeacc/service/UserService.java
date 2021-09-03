package com.homeacc.service;

import org.springframework.stereotype.Service;

import com.homeacc.dto.UserCredentialsDTO;
import com.homeacc.model.User;
import com.homeacc.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User authUser(UserCredentialsDTO credentialsDTO) {
        return userRepository.findByUsernameAndPassword(credentialsDTO.getUsername(), credentialsDTO.getPassword());
    }
}
