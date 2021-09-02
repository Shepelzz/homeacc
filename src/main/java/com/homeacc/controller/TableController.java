package com.homeacc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homeacc.model.Counter;
import com.homeacc.model.CounterData;
import com.homeacc.model.Month;
import com.homeacc.service.CounterDataService;

@Controller
@RequestMapping(path = "/")
public class TableController {
    private final CounterDataService counterDataService;

    @Autowired
    public TableController(CounterDataService counterDataService) {
        this.counterDataService = counterDataService;
    }

    @GetMapping
    public String getAllProperties(Model model, HttpServletResponse response) {
        List<CounterData> counterDataList =
            counterDataService.getAllCounterData(1);

        List<Counter> counters = counterDataList.stream()
            .map(CounterData::getCounter)
            .collect(Collectors.toList());

        List<Month> monthes = counterDataList.stream()
            .map(CounterData::getMonth)
            .collect(Collectors.toList());

        model.addAttribute("counterData", counterDataList);
        model.addAttribute("counters", counters);
        model.addAttribute("monthes", monthes);

        response.addCookie(new Cookie("test", "value"));

        return "table";
    }
}
