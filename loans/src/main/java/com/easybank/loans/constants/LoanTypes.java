package com.easybank.loans.constants;

public enum LoanTypes {

    HOME_LOAN("Home Loan"), CAR_LOAN("Car Loan"), PERSONAL_LOAN("Personal Loan");

    private String loanType;

    LoanTypes(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanType() {
        return loanType;
    }
}
