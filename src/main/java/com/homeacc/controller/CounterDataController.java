package com.homeacc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.homeacc.model.Counter;
import com.homeacc.model.CounterData;
import com.homeacc.service.CounterDataService;

@Controller
@RequestMapping(path = "/property/{propertyId}")
public class CounterDataController {
    private final CounterDataService counterDataService;

    @Autowired
    public CounterDataController(CounterDataService counterDataService) {
        this.counterDataService = counterDataService;
    }

    @GetMapping(path = "/data")
    public String getCounterData(Model model, @PathVariable int propertyId) {
        List<CounterData> counterDataList =
            counterDataService.getAllCounterData(propertyId);

        List<Counter> counters = counterDataList.stream()
            .map(CounterData::getCounter)
            .collect(Collectors.toList());

        model.addAttribute("counterData", counterDataList);
        model.addAttribute("counters", counters);
        return "counter-data";
    }

}
