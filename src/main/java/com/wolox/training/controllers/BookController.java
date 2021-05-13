package com.wolox.training.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private static final String NAME = "name";
    private static final String GREETING_VIEW ="greeting";

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute(NAME, name);
        return GREETING_VIEW;
    }

}
