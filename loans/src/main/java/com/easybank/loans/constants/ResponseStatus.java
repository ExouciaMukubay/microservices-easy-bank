package com.easybank.loans.constants;

public enum ResponseStatus {

    SUCCESS("200", "Request processed successfully"),
    CREATED("201", "Loan created successfully"),
    UPDATE_FAILED("417", "Update operation failed. Please try again or contact Dev team"),
    DELETE_FAILED("417", "Delete operation failed. Please try again or contact Dev team");;

    private String code;
    private String message;

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
