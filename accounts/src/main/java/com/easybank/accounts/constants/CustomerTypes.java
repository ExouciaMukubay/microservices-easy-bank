package com.easybank.accounts.constants;

public enum CustomerTypes {
    INDIVIDUAL("Individual"),
    CORPORATE("Corporate");

    private String customerType;

    private CustomerTypes(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerType() {
        return customerType;
    }
}
