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

import com.homeacc.model.Country;
import com.homeacc.model.Property;
import com.homeacc.repository.CountryRepository;
import com.homeacc.service.PropertyService;

@Controller
@RequestMapping(path = "/property")
public class PropertyController {
    private final PropertyService propertyService;
    private final CountryRepository countryRepository;

    @Autowired
    public PropertyController(PropertyService propertyService, CountryRepository countryRepository) {
        this.propertyService = propertyService;
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public String getAllProperties(Model model) {
        List<Property> list = propertyService.getAllProperties();

        model.addAttribute("properties", list);
        return "list-properties";
    }

    @GetMapping(path = { "/edit", "/edit/{id}" })
    public String editPropertyById(Model model, @PathVariable("id") Optional<Integer> id) {
        List<Country> countries = (List<Country>) countryRepository.findAll();
        model.addAttribute("countries", countries);
        if (id.isPresent()) {
            Property entity = propertyService.getPropertyById(id.get());
            model.addAttribute("property", entity);
        } else {
            model.addAttribute("property", new Property());
        }
        return "add-edit-property";
    }

    @GetMapping(path = "/delete/{id}")
    public String deletePropertyById(Model model, @PathVariable("id") Integer id) {
        propertyService.deletePropertyById(id);
        return "redirect:/property";
    }

    @PostMapping(path = "/add")
    public String createOrUpdateProperty(Property property) {
        propertyService.createOrUpdateProperty(property);
        return "redirect:/property";
    }
}
