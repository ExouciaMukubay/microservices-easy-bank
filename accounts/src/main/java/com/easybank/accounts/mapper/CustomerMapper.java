package com.easybank.accounts.mapper;

import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.entity.Customer;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer) {
        var customerDto = CustomerDto.builder()
                .customerAccountNumber(customer.getCustomerAccountNumber())
                .surname(customer.getSurname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .customerType(customer.getCustomerType())
                .address(customer.getAddress())
                .build();

        if (customer.getAccounts() != null && !customer.getAccounts().isEmpty()) {
            // convert List<Accounts> to List<AccountsDto>#
            var accountsDto = customer.getAccounts()
                    .stream()
                    .map(AccountsMapper::mapSingleAccountToAccountsDto)
                    .collect(Collectors.toList());
            customerDto.setAccountsDto(accountsDto);
        } else {
            customerDto.setAccountsDto(null);
        }
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {
        var customer = Customer.builder()
                .customerAccountNumber(customerDto.getCustomerAccountNumber())
                .surname(customerDto.getSurname())
                .lastname(customerDto.getLastname())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .customerType(customerDto.getCustomerType())
                .address(customerDto.getAddress())
                .build();

        if (customerDto.getAccountsDto() != null && !customerDto.getAccountsDto().isEmpty()) {
            // convert List<AccountsDto> to List<Accounts>
            var accounts = customerDto.getAccountsDto()
                    .stream()
                    .map(accountsDto -> AccountsMapper.mapSingleAccountsDtoToAccounts(accountsDto, customer))
                    .collect(Collectors.toList());
            customer.setAccounts(accounts);
        } else {
            customer.setAccounts(null);
        }
        return customer;
    }

    public static void updateCustomerAndMapTopCustomer(CustomerDto customerDto, Customer customer) {
        if (!customerDto.getSurname().isBlank() && !customerDto.getSurname().equals(customer.getSurname())) {
            customer.setSurname(customerDto.getSurname());
        }
        if (!customerDto.getLastname().isBlank() && !customerDto.getLastname().equals(customer.getLastname())) {
            customer.setLastname(customerDto.getLastname());
        }
        if (!customerDto.getEmail().isBlank() && !customerDto.getEmail().equals(customer.getEmail())) {
            customer.setEmail(customerDto.getEmail());
        }
        if (!customerDto.getMobileNumber().isBlank()
                && !customerDto.getMobileNumber().equals(customer.getMobileNumber())) {
            customer.setMobileNumber(customerDto.getMobileNumber());
        }

        if (!customerDto.getAddress().isBlank() && !customerDto.getAddress().equals(customer.getAddress())) {
            customer.setAddress(customerDto.getAddress());
        }

        if (customerDto.getAccountsDto() != null && !customerDto.getAccountsDto().isEmpty()
                && customerDto.getAccountsDto().size() != customer.getAccounts().size()) {
            var accounts = customerDto.getAccountsDto()
                    .stream()
                    .map(accountsDto -> AccountsMapper.mapSingleAccountsDtoToAccounts(accountsDto, customer))
                    .collect(Collectors.toList());
            customer.setAccounts(accounts);
        }
    }
}
