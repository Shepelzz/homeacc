package com.homeacc.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeacc.model.Counter;
import com.homeacc.model.CounterData;
import com.homeacc.repository.CounterDataRepository;

@Service
public class CounterDataService {
    private final CounterDataRepository counterDataRepository;

    @Autowired
    public CounterDataService(CounterDataRepository counterDataRepository) {
        this.counterDataRepository = counterDataRepository;
    }

    public List<CounterData> getAllCounterData(int propertyId) {
        List<CounterData> result = counterDataRepository.findByPropertyId(propertyId);

        if (result.size() > 0) {
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    public CounterData getCounterData(int counterId, int monthId) {
        return counterDataRepository.findByCounterId(counterId).stream()
            .filter(cd -> cd.getMonth().getId().equals(monthId))
            .findFirst().orElse(null);
    }
}
