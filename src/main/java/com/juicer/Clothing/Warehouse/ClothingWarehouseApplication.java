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
		repository.save(Item.builder()
				.name("Black Shirt")
				.brandFrom(Brand.BALENCIAGA)
				.yearCreated(2021)
				.price(1500).build());
		repository.save(Item.builder()
				.name("White Shirt")
				.brandFrom(Brand.STONE_ISLAND)
				.yearCreated(2023)
				.price(1500).build());
		repository.save(Item.builder()
				.name("Jeans")
				.brandFrom(Brand.DIOR)
				.yearCreated(2022)
				.price(5000).build());
		repository.save(Item.builder()
				.name("Hoodie")
				.brandFrom(Brand.STONE_ISLAND)
				.yearCreated(2025)
				.price(20000).build());
		repository.save(Item.builder()
				.name("Red Pants")
				.brandFrom(Brand.BALENCIAGA)
				.yearCreated(2023)
				.price(15000).build());
		repository.save(Item.builder()
				.name("Red Shirt")
				.brandFrom(Brand.STONE_ISLAND)
				.yearCreated(2023)
				.price(10000).build());
		repository.save(Item.builder()
				.name("Yellow Shirt")
				.brandFrom(Brand.DIOR)
				.yearCreated(2021)
				.price(1000).build());
		repository.save(Item.builder()
				.name("Blue Jeans")
				.brandFrom(Brand.STONE_ISLAND)
				.yearCreated(2023)
				.price(3000).build());
		repository.save(Item.builder()
				.name("Black Jeans")
				.brandFrom(Brand.BALENCIAGA)
				.yearCreated(2022)
				.price(6000).build());
		repository.save(Item.builder()
				.name("Shorts")
				.brandFrom(Brand.DIOR)
				.yearCreated(2024)
				.price(2000).build());
		repository.save(Item.builder()
				.name("Green Shorts")
				.brandFrom(Brand.DIOR)
				.yearCreated(2021)
				.price(1000).build());
		repository.save(Item.builder()
				.name("Red Jeans")
				.brandFrom(Brand.STONE_ISLAND)
				.yearCreated(2022)
				.price(3000).build());
		};
		}
}
