package com.homeacc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.homeacc.model.Counter;
import com.homeacc.model.CounterData;
import com.homeacc.model.Property;

public interface CounterRepository extends CrudRepository<Counter, Integer> {

    @Query("SELECT c FROM Counter c WHERE c.property.id = :propertyId")
    List<Counter> findByPropertyId(@Param("propertyId") int propertyId);

    @Query("SELECT DISTINCT c FROM Counter c "
        + "WHERE c.property.id = :propertyId "
        + "AND EXISTS ("
        + " SELECT cd FROM CounterData cd "
        + " WHERE cd.counter.id = c.id "
        + " AND (:yearMonthFrom = -1 OR cd.year*100+cd.month.id >= :yearMonthFrom) "
        + " AND (:yearMonthTo = -1 OR cd.year*100+cd.month.id <= :yearMonthFrom))"
        + "ORDER BY c.id")
    List<Counter> getAllCountersForPeriod(
        @Param("propertyId") int propertyId,
        @Param("yearMonthFrom") int yearMonthFrom,
        @Param("yearMonthTo") int yearMonthTo);
}
