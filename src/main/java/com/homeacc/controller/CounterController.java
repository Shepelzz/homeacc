package com.homeacc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homeacc.model.Counter;
import com.homeacc.service.CounterService;
import com.homeacc.service.PropertyService;

@Controller
@RequestMapping(path = "property/{propertyId}/counter")
public class CounterController {
    private final CounterService counterService;
    private final PropertyService propertyService;

    @Autowired
    public CounterController(CounterService counterService, PropertyService propertyService) {
        this.counterService = counterService;
        this.propertyService = propertyService;
    }

    @GetMapping
    public String getAllCounters(Model model, @PathVariable int propertyId) {
        model.addAttribute("property", propertyService.getPropertyById(propertyId));
        model.addAttribute("counters", counterService.getAllCounters(propertyId));
        return "list-counters";
    }

    @GetMapping(path = { "/edit", "/edit/{id}" })
    public String editCounterById(Model model, @PathVariable("id") Optional<Integer> id,
        @PathVariable int propertyId) {
        model.addAttribute("propertyId", propertyId);
        if (id.isPresent()) {
            Counter entity = counterService.getCounterById(id.get());
            model.addAttribute("counter", entity);
        } else {
            model.addAttribute("counter", new Counter());
        }
        return "add-edit-counter";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteCounterById(Model model, @PathVariable("id") Integer id,
        @PathVariable int propertyId) {
        counterService.deleteCounterById(id);
        return "redirect:/property/"+propertyId+"/counter";
    }

    @PostMapping(path = { "/edit", "/edit/{id}" })
    public String createOrUpdateCounter(Counter counter, @PathVariable int propertyId) {
        counterService.createOrUpdateCounter(counter, propertyId);
        return "redirect:/property/"+propertyId+"/counter";
    }
}
