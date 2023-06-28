package com.juicer.Clothing.Warehouse;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.juicer.Clothing.Warehouse.controller.HomeController;
import com.juicer.Clothing.Warehouse.model.Item;
import com.juicer.Clothing.Warehouse.model.Item.Brand;
import com.juicer.Clothing.Warehouse.repository.ItemRepository;

@SpringBootApplication
public class ClothingWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothingWarehouseApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository repository) { return args -> {
		repository.save(Item.builder()
				.name("Juicer Scent")
				.brandFrom(Brand.DIOR)
				.yearCreated(2022)
				.price(1000).build());
		repository.save(Item.builder()
				.name("Pokimane Handbag")
				.brandFrom(Brand.BALENCIAGA)
				.yearCreated(2021)
				.price(2500).build());
		repository.save(Item.builder()
				.name("Gamba T-shirt")
				.brandFrom(Brand.STONE_ISLAND)
				.yearCreated(2023)
				.price(2000).build());
		};
		}
}
