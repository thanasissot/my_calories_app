package com.mycalories.caloriesrest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/root")
    @PreAuthorize("hasRole('ROOT')")
    public String home() {
        return "This is the ROOT";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('USER')")
    public String dashboard() {
        return "This is the DASHBOARD";
    }

}
