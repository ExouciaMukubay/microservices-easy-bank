package com.easybank.accounts.mapper;


import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.entity.Accounts;
import com.easybank.accounts.entity.Customer;


public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return AccountsDto.builder()
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .customer(CustomerMapper.mapToCustomerDto(accounts.getCustomer()))
                .build();

    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        return accounts;
    }

    public static AccountsDto mapSingleAccountToAccountsDto(Accounts accounts) {
        return AccountsDto.builder()
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .build();
    }

    public static Accounts mapSingleAccountsDtoToAccounts(AccountsDto accountsDto, Customer customer) {
        return Accounts.builder()
                .accountNumber(accountsDto.getAccountNumber())
                .accountType(accountsDto.getAccountType())
                .customer(customer)
                .build();
    }


}
