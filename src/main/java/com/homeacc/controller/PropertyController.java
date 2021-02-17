package com.homeacc.controller;

import javax.validation.Valid;

import org.dom4j.rule.Mode;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homeacc.exception.ValidationException;
import com.homeacc.model.Property;
import com.homeacc.repository.PropertyRepository;
import com.homeacc.validation.PropertyValidation;

@Controller
@RequestMapping(path = "/property")
public class PropertyController {
    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping("/add")
    public String showSignUpForm(Property property) {
        return "property-add";
    }

    @PostMapping(path="/add")
    public String addProperty (@Valid Property property,
        BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "property-add";
        }

        propertyRepository.save(property);
        return "redirect:/property";
    }

    @GetMapping("/update/{id}")
    public String propertyUpdateForm(@PathVariable("id") int id, Model model) {
        Property p = propertyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid property Id:" + id));

        model.addAttribute("property", p);
        return "property-update";
    }

//    @PostMapping(value = "/update/{id}")
//    public String propertyUpdate(@PathVariable("id") int id,
//        @RequestBody Property property, Model model) {
//
//        try {
//            PropertyValidation.check(property);
//        } catch (ValidationException v) {
//            property.setId(id);
//            return "property-update";
//        }
//
//        propertyRepository.save(property);
//        return "redirect:/property";
//    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid Property property,
        BindingResult result, Model model) {
        if (result.hasErrors()) {
            property.setId(id);
            return "property-update";
        }

        propertyRepository.save(property);
        return "redirect:/property";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        Property p = propertyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid property Id:" + id));
        propertyRepository.delete(p);
        return "redirect:/property";
    }

    @GetMapping()
    public String getPropertiesPage(Model model) {
        model.addAttribute("properties", propertyRepository.findAll());
        model.addAttribute("property", new Property());
        return "property-list";
    }
}
