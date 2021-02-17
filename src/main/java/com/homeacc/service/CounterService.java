package com.homeacc.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeacc.model.Counter;
import com.homeacc.repository.CounterRepository;
import com.homeacc.repository.PropertyRepository;

@Service
public class CounterService {

    private static final String EXCEPTION_MESSAGE = "No counter record exist for given id";
    private final CounterRepository counterRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public CounterService(CounterRepository counterRepository, PropertyRepository propertyRepository) {
        this.counterRepository = counterRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<Counter> getAllCounters(int propertyId) {
        List<Counter> result = counterRepository.findByPropertyId(propertyId);

        if (result.size() > 0) {
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    public Counter getCounterById(int id) {
        Optional<Counter> counter = counterRepository.findById(id);
        return counter.orElseThrow(() -> new RuntimeException(EXCEPTION_MESSAGE));
    }

    public Counter createOrUpdateCounter(Counter entity, int propertyId) {
        entity.setProperty(propertyRepository.findById(propertyId)
            .orElseThrow(() -> new RuntimeException(EXCEPTION_MESSAGE)));

        if (Objects.isNull(entity.getId())) {
            return counterRepository.save(entity);
        } else {
            Optional<Counter> counter = counterRepository.findById(entity.getId());
            if (counter.isPresent()) {
                Counter newCounter = counter.get();
                newCounter.setName(entity.getName());
                newCounter.setSerialNumber(entity.getSerialNumber());
                newCounter.setProperty(entity.getProperty());
                return counterRepository.save(newCounter);
            } else {
                entity = counterRepository.save(entity);
                return entity;
            }
        }
    }

    public void deleteCounterById(int id) {
        Optional<Counter> counter = counterRepository.findById(id);

        if (counter.isPresent()) {
            counterRepository.deleteById(id);
        } else {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
    }
}
