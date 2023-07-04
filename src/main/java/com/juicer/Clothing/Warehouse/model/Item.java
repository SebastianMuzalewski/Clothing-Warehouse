package com.juicer.Clothing.Warehouse.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@Table
public class Item {
    @Id
    private Long id;
    @NotBlank(message = "Name cannot be blank!")
    private String name;
    @Min(value = 2021, message = "Year must be greater than 2021!")
    private int yearCreated;
    @Min(value = 1000, message = "The price must be set higher than $1000!")
    private double price;

    private Brand brandFrom;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    public enum Brand {
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


