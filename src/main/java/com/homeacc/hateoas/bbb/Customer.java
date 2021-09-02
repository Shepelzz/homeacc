package com.homeacc.hateoas.bbb;

import org.springframework.hateoas.RepresentationModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Customer extends RepresentationModel<Customer> {

    private String customerId;
    private String customerName;
    private String companyName;

    // standard getters and setters
}
