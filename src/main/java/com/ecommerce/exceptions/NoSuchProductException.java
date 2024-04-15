package com.ecommerce.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(Long id) {
        super( "No Product exists with ID-" + id );
    }
}
