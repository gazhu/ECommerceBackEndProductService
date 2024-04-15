package com.ecommerce.services;

import com.ecommerce.model.Vendor;
import com.ecommerce.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    Logger logger = LoggerFactory.getLogger( VendorServiceImpl.class );
    @Autowired
    VendorRepository repository;

    @Override
    public List<Vendor> viewAll() {
        logger.info( "Inside Vendor class viewAll method" );
        return repository.findAll();
    }

    @Override
    public Vendor findById(Long id) {
        logger.info( "Inside vendor class findById method" );
        return repository.findById( id ).orElse( null );
    }

    @Override
    public int save(Vendor vendor) {
        Long id = vendor.getVendorId();
        logger.info( "Inside Vendor class save method" );
        List<Vendor> tempList = repository.findAll();
        for (Vendor vendor2 : tempList) {
            if (vendor2.getVendorId() != null) {
                if (vendor2.getVendorId().equals( id )) {
                    repository.save( vendor );
                    return 0;
                }
            }
        }
        repository.save( vendor );
        return 1;
    }

    @Override
    public void delete(Long id) {
        logger.info( "Inside vendor class delete method" );
        repository.deleteById( id );
    }
}
