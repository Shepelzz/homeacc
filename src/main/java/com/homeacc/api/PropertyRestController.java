package com.homeacc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeacc.model.Property;
import com.homeacc.repository.PropertyRepository;

@RestController
@RequestMapping(path = "api/property")
public class PropertyRestController {
    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping()
    public @ResponseBody Iterable<Property> getAll() {
        return propertyRepository.findAll();
    }
}
