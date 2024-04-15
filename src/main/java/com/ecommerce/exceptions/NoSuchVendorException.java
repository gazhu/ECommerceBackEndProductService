package com.ecommerce.exceptions;

public class NoSuchVendorException extends RuntimeException {
    public NoSuchVendorException(Long id) {
        super( "No vendor exists with ID-" + id );
    }
}
