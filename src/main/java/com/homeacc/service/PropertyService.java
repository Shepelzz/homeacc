package com.homeacc.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeacc.model.Property;
import com.homeacc.repository.PropertyRepository;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        List<Property> result = (List<Property>) propertyRepository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    public Property getPropertyById(int id) throws RuntimeException {
        Optional<Property> property = propertyRepository.findById(id);
        return property.orElseThrow(() -> new RuntimeException("No property record exist for given id"));
    }

    public Property createOrUpdateProperty(Property entity) {
        if (Objects.isNull(entity.getId())) {
            return propertyRepository.save(entity);
        } else {
            Optional<Property> property = propertyRepository.findById(entity.getId());
            if (property.isPresent()) {
                Property newProperty = property.get();
                newProperty.setCity(entity.getCity());
                newProperty.setCountry(entity.getCountry());
                newProperty.setStreet(entity.getStreet());
                newProperty.setHouseNo(entity.getHouseNo());
                newProperty.setFlat(entity.getFlat());

                return propertyRepository.save(newProperty);
            } else {
                entity = propertyRepository.save(entity);

                return entity;
            }
        }
    }

    public void deletePropertyById(int id) throws RuntimeException {
        Optional<Property> property = propertyRepository.findById(id);

        if (property.isPresent()) {
            propertyRepository.deleteById(id);
        } else {
            throw new RuntimeException("No employee record exist for given id");
        }
    }
}
