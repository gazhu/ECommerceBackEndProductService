package com.ecommerce.controller;

import com.ecommerce.model.Products;
import com.ecommerce.services.ProductsServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/products/")
@RestController
public class ProductsController {
    Logger logger = LoggerFactory.getLogger( ProductsController.class );
    @Autowired
    ProductsServiceImpl productsServiceImpl;

    @GetMapping("{vendorId}")
    public List<Products> viewAllProducts(@PathVariable("vendorId") Long vendorId) {
        logger.info( "Request to Fetch All product records for vendor Id " + vendorId );
        List<Products> productsList = productsServiceImpl.viewAll( vendorId );
        if (productsList.size() != 0) {
            logger.trace( "All Product records fetched successfully" );
            return productsList;
        }
        logger.error( "No Products found for the given vendor Id" );
        return productsList;
    }

    @GetMapping("product/{productId}")
    public Products viewProductById(@PathVariable("productId") Long productId) {
        Products products = productsServiceImpl.findById( productId );
        logger.info( "Request to fetch details about product with product Id " + productId );
        if (products != null) {
            logger.trace( "Product found successfully" );
            return products;
        }
        logger.error( "No such Product exists" );
        return products;
    }

    @PostMapping("save")
    public ResponseEntity<String> saveProduct(@Valid @RequestBody Products products) {
        Long id = products.getProductId();
        String name = products.getProductName();
        logger.info( "Request to save new Product with Product Id " + id );
        int res = productsServiceImpl.save( products );
        if (res == 1) {
            logger.trace( "Product " + name + " has been saved successfully" );
            return new ResponseEntity<>(
                    "Product " + name + " has been saved",
                    HttpStatus.OK );
        } else if (res == 2) {
            logger.error( "Product " + name + " is already present in the DB" );
            return new ResponseEntity<>( "Product is already listed for this vendor", HttpStatus.BAD_REQUEST );
        } else {
            logger.trace( "Product updated " + id + " successfully" );
            return new ResponseEntity<>( "Product " + id + "-" + name + " updated successfully", HttpStatus.OK );
        }
    }

    @DeleteMapping("delete/{productId}/{vendorId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable("productId") Long productId, @PathVariable("vendorId") Long vendorId) {
        logger.info( "Request to delete product with Product Id" + productId );
        if (productsServiceImpl.findById( productId ) == null) {
            logger.error( "No Product with Product ID-" + productId + " exists" );
            return new ResponseEntity<>( "No Product with Product ID-" + productId + " exists",
                    HttpStatus.BAD_REQUEST );
        } else {
            productsServiceImpl.delete( productId );
            if (productsServiceImpl.findById( productId ) != null) {
                logger.error( "Product-" + productId + " couldn't be deleted" );
                return new ResponseEntity<>( "Product-" + productId + " couldn't be deleted", HttpStatus.BAD_REQUEST );
            } else {
                logger.trace( "Product-" + productId + " deleted successfully" );
                return new ResponseEntity<>( "Product-" + productId + " deleted successfully", HttpStatus.OK );
            }


        }
    }

    @GetMapping("storefront/{productId}")
    public Boolean findProductByIdStoreFront(@PathVariable("productId") Long productId){
        Products products = productsServiceImpl.findById( productId );
        logger.info( "Request to fetch details about product with product Id " + productId );
        if (products != null) {
            logger.trace( "Product found successfully" );
            return true;
        }
        logger.error( "No such Product exists" );
        return false;
    }

}

