package com.space.repository;

import com.space.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductsRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
