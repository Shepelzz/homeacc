package com.homeacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.homeacc.model.Counter;
import com.homeacc.model.CounterData;

public interface CounterDataRepository extends CrudRepository<CounterData, Integer> {

    @Query("SELECT c FROM CounterData c "
        + "WHERE c.counter.id = :counterId")
    List<CounterData> findByCounterId(@Param("counterId") int counterId);

    @Query("SELECT cd FROM CounterData cd "
        + "JOIN FETCH cd.counter c "
        + "JOIN FETCH cd.month m "
        + "WHERE c.property.id = :propertyId "
        + "ORDER BY cd.year, m.id, c.id")
    List<CounterData> findByPropertyId(@Param("propertyId") int propertyId);

    @Query("SELECT cd FROM CounterData cd "
        + "JOIN FETCH cd.counter c "
        + "JOIN FETCH cd.month m "
        + "WHERE c.property.id = :propertyId "
        + "AND (:yearMonthFrom = -1 OR cd.year*100+cd.month.id >= :yearMonthFrom) "
        + "AND (:yearMonthTo = -1 OR cd.year*100+cd.month.id <= :yearMonthFrom)"
        + "ORDER BY cd.year, cd.month.id, c.id")
    List<Counter> getAllDataForPeriod(
        @Param("propertyId") int propertyId,
        @Param("yearMonthFrom") int yearMonthFrom,
        @Param("yearMonthTo") int yearMonthTo);
}