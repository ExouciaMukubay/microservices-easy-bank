package com.easybank.accounts.service;

import com.easybank.accounts.dto.AccountsDto;


public interface AccountsServiceI {

    /**
     * Fetchs the account details by account number
     *
     * @param accountNumber
     * @return account details
     */
    AccountsDto fetchAccountDetails(Long accountNumber);

    void createAccount(AccountsDto accountsDto, Long customerAccountNumber);

    boolean deleteAccount(Long accountNumber);
}
