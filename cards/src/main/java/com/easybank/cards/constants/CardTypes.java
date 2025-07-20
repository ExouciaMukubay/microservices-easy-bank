package com.easybank.cards.constants;

public enum CardTypes {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    PREPAID_CARD("Prepaid Card"),
    LOAN_CARD("Loan Card");

    private String cardType;

    CardTypes(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }
}
