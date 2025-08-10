package com.easybank.accounts.service.impl;

import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.exception.AccountAlreadyExistsException;
import com.easybank.accounts.exception.ResourceNotFoundException;
import com.easybank.accounts.mapper.AccountsMapper;
import com.easybank.accounts.repository.AccountsRepository;
import com.easybank.accounts.repository.CustomerRepository;
import com.easybank.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AccountsDto fetchAccountDetails(Long accountNumber) {
        var optionalAccount = accountsRepository.findByAccountNumber(accountNumber);

        if (optionalAccount.isEmpty()) {
            throw new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString());
        }

        return AccountsMapper.mapSingleAccountToAccountsDto(optionalAccount.get());
    }

    @Override
    public void createAccount(AccountsDto accountsDto) {
        // check if account number exists
        var optionalAccount = accountsRepository.findByAccountNumber(accountsDto.getAccountNumber());

        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistsException("accountNumber",
                    accountsDto.getAccountNumber().toString());
        }

        // check if customer account number exists
        var optionalCustomer = customerRepository.findByCustomerAccountNumber(accountsDto.getCustomerAccountNumber());

        if (optionalCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "customerAccountNumber",
                    accountsDto.getCustomerAccountNumber().toString());
        } else {

            var account = AccountsMapper.mapSingleAccountsDtoToAccounts(accountsDto, optionalCustomer.get());

            accountsRepository.save(account);
        }
    }

    @Override
    public boolean deleteAccount(Long accountNumber) {
        if (accountsRepository.findByAccountNumber(accountNumber).isEmpty()) {
            throw new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString());
        }

        return accountsRepository.deleteByAccountNumber(accountNumber);
    }
}
