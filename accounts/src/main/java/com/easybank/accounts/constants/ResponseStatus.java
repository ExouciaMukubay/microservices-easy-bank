package com.easybank.accounts.constants;

import lombok.Getter;


@Getter
public enum ResponseStatus {

    CREATED("201", "Account created successfully"),
    SUCCESS("200", "Request processed successfully"),
    UPDATE_FAILED("417", "Update operation failed. Please try again or contact Dev team"),
    DELETE_FAILED("417", "Delete operation failed. Please try again or contact Dev team");;

    private String code;
    private String message;

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
