package com.juicer.Clothing.Warehouse.controller;

import com.juicer.Clothing.Warehouse.model.Item;
import com.juicer.Clothing.Warehouse.model.User;
import com.juicer.Clothing.Warehouse.model.dto.ItemSearchByDateDto;
import com.juicer.Clothing.Warehouse.repository.ItemRepositoryPaginated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.juicer.Clothing.Warehouse.repository.ItemRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/itemList")
public class ItemListController {
    private static final int PAGE_SIZE = 8;
    private ItemRepository itemRepository;
    private ItemRepositoryPaginated itemRepositoryPaginated;

    public ItemListController(ItemRepository itemRepository, ItemRepositoryPaginated itemRepositoryPaginated){
        this.itemRepository = itemRepository;
        this.itemRepositoryPaginated = itemRepositoryPaginated;
    }

    @GetMapping
    public String itemList(Model model) {
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

        return "itemList";
    }

    @ModelAttribute
    public void items(Model model){
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("items", itemPage);
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
    }

    @ModelAttribute
    public void itemsByDateDto(Model model) {
        model.addAttribute("itemsByDateDto", new ItemSearchByDateDto());
    }

    @PostMapping("/search")
    public String searchItemsByBrandAndYear(
            @RequestParam("brand") Item.Brand brand,
            @RequestParam("year") int year,
            Model model
    ) {
        List<Item> items = itemRepository.findByBrandFromAndYearCreated(brand, year);
        model.addAttribute("items", items);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = null;
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            userRole = authentication.getAuthorities().iterator().next().getAuthority();
        }
        model.addAttribute("userRole", userRole);

        boolean hasRoleAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);

        return "itemList";
    }

    @PostMapping("/pickSort")
    public String sortItems(@RequestParam("sortBy") String sortBy, Model model) {
        Page<Item> itemPage = null;

        switch (sortBy) {
            case "name":
                itemPage = itemRepositoryPaginated.findAllByOrderByNameAsc(PageRequest.of(0, PAGE_SIZE));
                break;
            case "price":
                itemPage = itemRepositoryPaginated.findAllByOrderByPriceDesc(PageRequest.of(0, PAGE_SIZE));
                break;
            case "brand":
                itemPage = itemRepositoryPaginated.findAllByOrderByBrandFromAsc(PageRequest.of(0, PAGE_SIZE));
                break;
            default:
                itemPage = itemRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
                break;
        }

        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = null;
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            userRole = authentication.getAuthorities().iterator().next().getAuthority();
        }
        model.addAttribute("userRole", userRole);

        boolean hasRoleAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);

        return "itemList";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model, @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch,
                             @RequestParam(value = "sortBy", required = false) String sortBy) {

        int page = pageToSwitch.orElse(0);
        int totalPages = (int) model.getAttribute("totalPages");

        if (page < 0 || page >= totalPages) {
            return "itemList";
        }

        Page<Item> itemPage;

        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    itemPage = itemRepositoryPaginated.findAllByOrderByNameAsc(PageRequest.of(page, PAGE_SIZE));
                    break;
                case "price":
                    itemPage = itemRepositoryPaginated.findAllByOrderByPriceDesc(PageRequest.of(page, PAGE_SIZE));
                    break;
                case "brand":
                    itemPage = itemRepositoryPaginated.findAllByOrderByBrandFromAsc(PageRequest.of(page, PAGE_SIZE));
                    break;
                default:
                    itemPage = itemRepositoryPaginated.findAll(PageRequest.of(page, PAGE_SIZE));
                    break;
            }
        } else {
            itemPage = itemRepositoryPaginated.findAll(PageRequest.of(page, PAGE_SIZE));
        }

        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = null;
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            userRole = authentication.getAuthorities().iterator().next().getAuthority();
        }

        model.addAttribute("userRole", userRole);

        boolean hasRoleAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);

        return "itemList";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam("id") Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/itemList";
    }
}