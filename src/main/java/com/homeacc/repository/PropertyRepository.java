package com.homeacc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homeacc.model.Property;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
}
