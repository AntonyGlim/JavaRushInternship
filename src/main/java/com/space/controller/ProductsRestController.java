package com.space.controller;

import com.space.model.Product;
import com.space.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/v1/products")
public class ProductsRestController {

    ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    //  http://localhost:8080/rest/v1/products/
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> showProducts(){
        return productsService.findAll();
    }
}
