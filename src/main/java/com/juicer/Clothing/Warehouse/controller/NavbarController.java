package com.juicer.Clothing.Warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarController {

    @GetMapping("/navbar-about")
    public String aboutPage(Model model) {
        return "about";
    }

    @GetMapping("/navbar-design")
    public String designPage(Model model) {
        return "design";
    }

    @GetMapping("/navbar-home")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/navbar-itemList")
    public String itemPage(Model model) {
        return "itemList";
    }

    @GetMapping("/navbar-itemManagement")
    public String itemManagementPage(Model model) {
        return "itemManagement";
    }

    @GetMapping("/navbar-login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/navbar-register")
    public String registerPage(Model model) {
        return "register";
    }
}