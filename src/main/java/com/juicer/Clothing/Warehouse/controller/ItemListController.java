package com.juicer.Clothing.Warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juicer.Clothing.Warehouse.repository.ItemRepository;

@Controller
@RequestMapping("/itemList")
public class ItemListController {
    private ItemRepository itemRepository;

    public ItemListController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String showItems(Model model){
        return "itemList";
    }

    @ModelAttribute
    public void items(Model model){
        model.addAttribute("items", itemRepository.findAll());
    }
}
