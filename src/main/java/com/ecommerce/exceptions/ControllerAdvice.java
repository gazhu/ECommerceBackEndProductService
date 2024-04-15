package com.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoRecordFoundException.class})
    public ResponseEntity<Object> handleNoRecordFoundException(NoRecordFoundException ex, WebRequest webRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put( "timeStamp", LocalDateTime.now() );
        map.put( "message", ex.getMessage() );
        return new ResponseEntity<>( map, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler({NoSuchVendorException.class})
    public ResponseEntity<Object> handleNoSuchVendorException(NoSuchVendorException ex, WebRequest webRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put( "timeStamp", LocalDateTime.now() );
        map.put( "message", "No such Vendor Found" );
        return new ResponseEntity<>( map, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler({NoSuchProductException.class})
    public ResponseEntity<Object> handleNoSuchProductException(NoSuchProductException ex, WebRequest webRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put( "timeStamp", LocalDateTime.now() );
        map.put( "message", "No such Product Found" );
        return new ResponseEntity<>( map, HttpStatus.NOT_FOUND );
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("status", status.value());
//
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect( Collectors.toList());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }
}
