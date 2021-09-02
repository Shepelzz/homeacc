package com.homeacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.homeacc.model.Counter;
import com.homeacc.model.Month;

public interface MonthRepository extends CrudRepository<Month, Integer> {
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
