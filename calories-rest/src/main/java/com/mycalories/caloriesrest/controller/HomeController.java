package com.mycalories.caloriesrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "This is the HOME";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "This is the DASHBOARD";
    }

}
