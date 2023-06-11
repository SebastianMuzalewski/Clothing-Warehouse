package com.juicer.Clothing.Warehouse.controller;

import com.juicer.Clothing.Warehouse.model.item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.EnumSet;

@Controller
@RequestMapping("/design")
public class DesignController {

    @GetMapping
    public String design(){ return "design"; }

}

