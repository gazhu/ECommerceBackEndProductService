package com.ecommerce.controller;

import com.ecommerce.model.Vendor;
import com.ecommerce.services.VendorServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor/")
public class VendorController {
    Logger logger = LoggerFactory.getLogger( VendorController.class );
    @Autowired
    VendorServiceImpl vendorService;

    @GetMapping
    public List<Vendor> viewAll() {
        List<Vendor> vendors = vendorService.viewAll();
        logger.info( "Request to Fetch All vendor records" );
        if (vendors.size() != 0) {
            logger.trace( "All vendor records fetched successfully" );
            return vendors;
        }
        logger.error( "No Vendors found" );
        return vendors;
    }

    @GetMapping("{vendorId}")
    public Vendor findById(@PathVariable("vendorId") Long vendorId) {
        Vendor vendor = vendorService.findById( vendorId );
        logger.info( "Request to fetch details about vendor with vendor Id " + vendorId );
        if (vendor != null) {
            logger.trace( "Vendor found successfully" );
            return vendor;
        }
        logger.error( "No such Vendor exists" );
        return vendor;
    }

    @PostMapping("save")
    public ResponseEntity<String> saveVendor(@Valid @RequestBody Vendor vendor) {
        logger.info( "Request to save new Vendor with Vendor Id " + vendor.getVendorId() );
        if (vendorService.save( vendor ) == 1) {
            logger.trace( "Vendor " + vendor.getVendorId() + " saved successfully" );
            return new ResponseEntity<>(
                    "Vendor " + vendor.getVendorId() + "-" + vendor.getVendorName() + " has been saved successfully",
                    HttpStatus.OK );
        } else {
            logger.trace( "Vendor " + vendor.getVendorId() + " updated successfully" );
            return new ResponseEntity<>(
                    "Vendor " + vendor.getVendorId() + "-" + vendor.getVendorName() + " updated successfully",
                    HttpStatus.OK );
        }
    }

    @DeleteMapping("delete/{vendorId}")
    public ResponseEntity<String> deleteVendor(@PathVariable("vendorId") Long vendorId) {
        logger.info( "Request to delete vendor with Vendor Id" + vendorId );
        if (vendorService.findById( vendorId ) == null) {
            logger.error( "No Vendor with Vendor ID-" + vendorId + " exists" );
            return new ResponseEntity<>( "No Vendor with Vendor ID-" + vendorId + " exists", HttpStatus.BAD_REQUEST );
        } else {
            vendorService.delete( vendorId );
            if (vendorService.findById( vendorId ) != null) {
                logger.error( "Vendor-" + vendorId + " couldn't be deleted" );
                return new ResponseEntity<>( "Vendor-" + vendorId + " couldn't be deleted", HttpStatus.BAD_REQUEST );
            } else {
                logger.trace( "Vendor-" + vendorId + " deleted successfully" );
                return new ResponseEntity<>( "Vendor-" + vendorId + " deleted successfully", HttpStatus.OK );
            }
        }
    }

}