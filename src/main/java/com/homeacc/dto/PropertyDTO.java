package com.homeacc.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PropertyDTO {
    private String id;
    private String countryId;
    private String city;
    private String street;
    private String houseNo;
    private String flat;
}
