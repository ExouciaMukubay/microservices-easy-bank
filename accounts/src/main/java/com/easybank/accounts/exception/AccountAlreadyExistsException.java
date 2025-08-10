package com.easybank.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String fieldName, String fieldValue) {
        super(String.format("Account already exists with the given input data %s : '%s'", fieldName,
                fieldValue));
    }
}
