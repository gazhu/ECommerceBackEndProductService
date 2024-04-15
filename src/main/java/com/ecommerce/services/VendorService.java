package com.ecommerce.services;

import com.ecommerce.model.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> viewAll();

    Vendor findById(Long id);

    int save(Vendor vendor);

    void delete(Long id);

}
