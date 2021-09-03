package com.homeacc.api;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.homeacc.dto.UserCredentialsDTO;
import com.homeacc.model.User;
import com.homeacc.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String authenticate(UserCredentialsDTO creds, Model model, HttpServletResponse resp) throws Exception {
        if(creds.getPassword() == null) {
            throw new IllegalArgumentException("Password not provided");
        }
        if(creds.getUsername() == null) {
            throw new IllegalArgumentException("Username not provided");
        }

        User user = userService.authUser(creds);
        if (Objects.isNull(user)) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }

        resp.addCookie(new Cookie("userName", user.getId().toString()));
        return "redirect:/property";
    }
}
