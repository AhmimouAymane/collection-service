package com.iotwastecollection.collection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class WebController {

    @GetMapping("/drivers")
    public String driversPage() {
        return "drivers-ui";
    }
}