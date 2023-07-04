package com.juicer.Clothing.Warehouse.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemSearchByDateDto {
    private String name;
    private String startDate;
    private String endDate;
}
