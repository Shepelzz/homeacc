package com.homeacc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class UserDTO extends UserCredentialsDTO {

    private String id;
    private String firstName;
    private String lastName;
}
