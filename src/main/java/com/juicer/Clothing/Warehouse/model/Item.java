package com.juicer.Clothing.Warehouse.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Long id;
    @NotBlank(message = "Name cannot be blank!")
    private String name;
    private Brand brandFrom;
    @Min(value = 2021, message = "Year must be greater than 2021!")
    private int yearCreated;
    @Min(value = 1000, message = "The price must be set higher than $1000!")
    private double price;

    public enum Brand {

        // Temporary names, We can change this shit to be cooler later
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior");

        private String title;

        private Brand(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

}


