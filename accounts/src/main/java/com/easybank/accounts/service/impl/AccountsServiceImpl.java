package com.easybank.accounts.service.impl;

import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.exception.ResourceNotFoundException;
import com.easybank.accounts.mapper.AccountsMapper;
import com.easybank.accounts.repository.AccountsRepository;
import com.easybank.accounts.repository.CustomerRepository;
import com.easybank.accounts.service.AccountsServiceI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountsServiceImpl implements AccountsServiceI {

    private final AccountsRepository repo;
    private final CustomerRepository customerRepository;

    @Override
    public AccountsDto fetchAccountDetails(String accountNumber) {
        var optionalAccount = repo.findByAccountNumber(accountNumber);

        if (!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found", "accountNumber", accountNumber);
        }

        return AccountsMapper.mapSingleAccountToAccountsDto(optionalAccount.get());
    }

    @Override
    public void createAccount(AccountsDto accountsDto, Long customerAccountNumber) {
        var customer = customerRepository.findByCustomerAccountNumber(customerAccountNumber);

        if (!customer.isPresent()) {
            throw new ResourceNotFoundException("Customer not found", "customerAccountNumber", customerAccountNumber.toString());
        } else {

            var account = AccountsMapper.mapSingleAccountsDtoToAccounts(accountsDto, customer.get());
            customer.get().getAccounts().add(account);
            repo.save(account);
            customerRepository.save(customer.get());
        }


    }


    @Override
    public boolean deleteAccount(String accountNumber) {
        if (!repo.findByAccountNumber(accountNumber).isPresent()) {
            throw new ResourceNotFoundException("Account not found", "accountNumber", accountNumber);
        }

        return repo.deleteByAccountNumber(accountNumber);
    }
}
