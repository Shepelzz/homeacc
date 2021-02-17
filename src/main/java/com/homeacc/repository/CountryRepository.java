package com.homeacc.repository;

import org.springframework.data.repository.CrudRepository;

import com.homeacc.model.Country;
import com.homeacc.model.Property;

public interface CountryRepository extends CrudRepository<Country, Integer> {
}
