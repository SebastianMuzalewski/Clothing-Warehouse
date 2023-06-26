package com.juicer.Clothing.Warehouse.repository;

import org.springframework.data.repository.CrudRepository;
import com.juicer.Clothing.Warehouse.model.Item;
public interface ItemRepository extends CrudRepository<Item,Long>{
}
