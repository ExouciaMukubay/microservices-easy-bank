package com.easybank.accounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String fieldName, String fieldValue) {
        super(String.format("Customer already exists with the given input data %s : '%s'", fieldName,
                fieldValue));
    }
}