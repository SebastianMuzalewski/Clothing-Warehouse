package com.juicer.Clothing.Warehouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.juicer.Clothing.Warehouse.model.Item;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long>{
    List<Item> findByBrandFromAndYearCreated(Item.Brand brand, int year);

    List<Item> findByNameStartsWithAndCreatedAtBetween(String name, LocalDate startDate, LocalDate endDate);
}
