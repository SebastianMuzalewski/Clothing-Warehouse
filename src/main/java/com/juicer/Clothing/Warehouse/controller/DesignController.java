package com.juicer.Clothing.Warehouse.controller;

import com.juicer.Clothing.Warehouse.model.Item;
import com.juicer.Clothing.Warehouse.model.Item.Brand;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.EnumSet;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {

    @GetMapping
    public String design(){ return "design"; }


    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
        log.info("brands converted to string:  {}", brands);
    }

//    @ModelAttribute
//    public Item item() {
//        return Item
//                .builder()
//                .brandFrom(Item.Brand.BALENCIAGA) // set default brand
//                .build();
//    }

    @ModelAttribute
    public Item item() {
        var brands = EnumSet.allOf(Brand.class);
        var defaultBrand = brands.iterator().next(); // Get the first brand from the set
        return Item.builder()
                .brandFrom(defaultBrand)
                .build();
    }


    @PostMapping
    public String processFighterAddition(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "design";
        }
        return "redirect:/design";
    }

}

