package com.ecommerce.services;

import com.ecommerce.model.Products;

import java.util.List;

public interface ProductsService {

    List<Products> viewAll(Long vendorId);

    Products findById(Long productId);

    int save(Products product);

    void delete(Long productId);

}
