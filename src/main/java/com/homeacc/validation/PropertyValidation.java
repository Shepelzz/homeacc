package com.homeacc.validation;

import java.util.Objects;

import com.homeacc.exception.ValidationException;
import com.homeacc.model.Property;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertyValidation {

    public static void check(Property property) throws ValidationException{
        if(Objects.isNull(property)) {
            throw new ValidationException("bred");
        }
    }
}
