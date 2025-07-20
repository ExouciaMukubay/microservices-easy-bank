package com.easybank.accounts.constants;

public enum AccountsTypes {
    SAVINGS("Savings"),
    CHECKING("Checking"), //Girokonto
    CURRENT("Current"), // Geschäftskonto
    LOAN("Loan");

    private String accountType;

    private AccountsTypes(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}