package com.homeacc.dto;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
@Component
public class UserCredentialsDTO {

    private String username;
    private String password;
}
