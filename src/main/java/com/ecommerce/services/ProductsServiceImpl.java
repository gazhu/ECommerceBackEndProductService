package com.ecommerce.services;

import com.ecommerce.exceptions.NoRecordFoundException;
import com.ecommerce.model.Products;
import com.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    Logger logger = LoggerFactory.getLogger( ProductsServiceImpl.class );
    @Autowired
    ProductRepository repository;

    @Override
    public List<Products> viewAll(Long vendorId) {
        logger.info( "Inside Products class viewAll method" );
        List<Products> correctProductsList = new ArrayList<>();
        List<Products> productsList = repository.findAll();
        for (Products products : productsList) {
            if (products.getVendorId() != null) {
                if (products.getVendorId().equals( vendorId )) {
                    correctProductsList.add( products );
                }
            } else {
                throw new NoRecordFoundException( vendorId );
            }
        }
        return correctProductsList;
    }

    @Override
    public Products findById(Long productId) {
        logger.info( "Inside Products class findById method" );
        return repository.findById( productId ).orElse( null );
    }

    @Override
    public int save(Products products) {
        logger.info( "Inside Products class save method" );
        Long id = products.getProductId();
        List<Products> tempList = viewAll( products.getVendorId() );
        for (Products product2 : tempList) {
            if (product2.getProductId() != null) {
                if (product2.getProductId().equals( id )) {
                    repository.save( products );
                    return 0;
                } else if (product2.getProductName().equals( products.getProductName() )) {
                    return 2;
                }
            }

        }
        repository.save( products );
        return 1;
    }


    @Override
    public void delete(Long productId) {
        logger.info( "Inside Products class delete method" );
        repository.deleteById( productId );
    }

}
