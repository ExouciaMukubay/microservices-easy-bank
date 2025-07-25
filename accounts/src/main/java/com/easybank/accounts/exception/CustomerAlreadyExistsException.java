package com.easybank.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String ressourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exists with the given input data %s : '%s'", ressourceName, fieldName, fieldValue));
    }

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

}