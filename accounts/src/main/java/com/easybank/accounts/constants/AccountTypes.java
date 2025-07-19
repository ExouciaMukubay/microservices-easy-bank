package com.easybank.accounts.constants;

public enum AccountTypes {
    SAVINGS("Savings"),
    CHECKING("Checking"), //Girokonto
    CURRENT("Current"), // Geschäftskonto
    LOAN("Loan");

    private String accountType;

    private AccountTypes(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}