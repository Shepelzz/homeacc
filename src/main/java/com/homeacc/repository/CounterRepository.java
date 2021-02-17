package com.homeacc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.homeacc.model.Counter;
import com.homeacc.model.Property;

public interface CounterRepository extends CrudRepository<Counter, Integer> {

    @Query("SELECT c FROM Counter c WHERE c.property.id = :propertyId")
    List<Counter> findByPropertyId(@Param("propertyId") int propertyId);
}
