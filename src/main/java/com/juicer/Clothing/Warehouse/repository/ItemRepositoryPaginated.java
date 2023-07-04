package com.juicer.Clothing.Warehouse.repository;

import com.juicer.Clothing.Warehouse.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepositoryPaginated extends PagingAndSortingRepository<Item, Long> {
    Page<Item> findAllByOrderByNameAsc(Pageable pageable);

    Page<Item> findAllByOrderByPriceDesc(Pageable pageable);

    Page<Item> findAllByOrderByBrandFromAsc(Pageable pageable);
}
