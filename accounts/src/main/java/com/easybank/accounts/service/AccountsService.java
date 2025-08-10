package com.easybank.accounts.service;

import com.easybank.accounts.dto.AccountsDto;


public interface AccountsService {

    /**
     * Fetchs the account details by account number
     *
     * @param accountNumber
     * @return account details
     */
    AccountsDto fetchAccountDetails(Long accountNumber);

    void createAccount(AccountsDto accountsDto);

    boolean deleteAccount(Long accountNumber);
}
