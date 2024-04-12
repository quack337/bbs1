package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.skhu.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping({"/", "index"})
    public String index() {
        return "home/index";
    }

    @GetMapping("login")
    public String login() {
        return "home/login";
    }
}