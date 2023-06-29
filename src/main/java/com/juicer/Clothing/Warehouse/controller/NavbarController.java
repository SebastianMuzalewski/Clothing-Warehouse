package com.juicer.Clothing.Warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarController {

    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/navbar-about")
    public String aboutPage(Model model) {
        return "about";
    }

    @GetMapping("/navbar-design")
    public String designPage(Model model) {
        return "design";
    }
}