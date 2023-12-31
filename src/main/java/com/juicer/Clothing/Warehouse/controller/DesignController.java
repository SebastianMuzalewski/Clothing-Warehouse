package com.juicer.Clothing.Warehouse.controller;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juicer.Clothing.Warehouse.model.Item;
import com.juicer.Clothing.Warehouse.model.Item.Brand;
import com.juicer.Clothing.Warehouse.repository.ItemRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {

    @Autowired
    private ItemRepository itemRepository;
    @GetMapping
    public String design(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasRoleAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);

        boolean hasRoleEmp = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"));
        model.addAttribute("hasRoleEmp", hasRoleEmp);

        String userRole = null;
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            userRole = authentication.getAuthorities().iterator().next().getAuthority();
        }
        model.addAttribute("userRole", userRole);

        return "design";
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
        log.info("brands converted to string:  {}", brands);
    }

    @ModelAttribute
    public Item item() {
        return Item
                .builder()
                .build();
    }

    @PostMapping
    public String processItemAddition(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "design";
        }
        log.info("Processing item: {}", item);
        itemRepository.save(item);
        return "redirect:/itemList";
    }
}

