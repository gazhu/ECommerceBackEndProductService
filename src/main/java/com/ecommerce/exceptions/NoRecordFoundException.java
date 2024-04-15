package com.ecommerce.exceptions;

public class NoRecordFoundException extends RuntimeException {
    public NoRecordFoundException(Long id) {
        super( "No record found for Id-" + id );
    }

    public NoRecordFoundException() {
        super( "No Record found" );
    }
}
