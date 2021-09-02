package com.homeacc.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeacc.dto.YearMonthDTO;
import com.homeacc.repository.CounterDataRepository;
import com.homeacc.repository.MonthRepository;

@Service
public class PeriodService {
    private final MonthRepository monthRepository;
    private final CounterDataRepository counterDataRepository;

    @Autowired
    public PeriodService(MonthRepository monthRepository, CounterDataRepository counterDataRepository) {
        this.monthRepository = monthRepository;
        this.counterDataRepository = counterDataRepository;
    }

    public List<YearMonthDTO> getPeriod(int yearMonthFrom, int yearMonthTo) {
        return new LinkedList<>();

    }
}
