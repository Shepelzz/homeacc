package com.homeacc.controller;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.homeacc.dto.UserCredentialsDTO;
import com.homeacc.dto.UserDTO;
import com.homeacc.model.User;
import com.homeacc.repository.UserRepository;
import com.homeacc.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {



    @GetMapping(path = "/login")
    public String getLoginPage(Model model) {
        model.addAttribute("creds", new UserCredentialsDTO());
        return "login";
    }



    @GetMapping(path = "/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        //TODO process
        return "redirect:/property";
    }
}
