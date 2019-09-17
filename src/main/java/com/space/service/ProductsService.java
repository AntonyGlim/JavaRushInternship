package com.space.service;

import com.space.model.Product;
import com.space.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList();
        productsRepository.findAll().forEach(list::add);
        return list;
    }
}