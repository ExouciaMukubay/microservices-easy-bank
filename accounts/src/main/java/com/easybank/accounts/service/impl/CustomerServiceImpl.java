package com.easybank.accounts.service.impl;

import com.easybank.accounts.constants.CustomerTypes;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.entity.Customer;
import com.easybank.accounts.exception.CustomerAlreadyExistsException;
import com.easybank.accounts.exception.ResourceNotFoundException;
import com.easybank.accounts.mapper.CustomerMapper;
import com.easybank.accounts.repository.CustomerRepository;
import com.easybank.accounts.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        var optionalCustomer = customerRepository.findByCustomerAccountNumber(customerDto.getCustomerAccountNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("customerAccountNumber", customerDto.getCustomerAccountNumber().toString());
        }

        var customer = CustomerMapper.mapToCustomer(customerDto);
        customerRepository.save(customer);

        log.info("New Account " + customer.getCustomerAccountNumber() + " " + customer.getSurname() + " " + customer.getLastname() + "created successfully");
    }

    @Override
    public CustomerDto fetchCustomerDetails(Long customerAccountNumber) {
        var customer = customerRepository
                .findByCustomerAccountNumber(customerAccountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerAccountNumber",
                        customerAccountNumber.toString())
                );

        log.info("Account details fetched successfully");

        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public Page<CustomerDto> getAllCustomers(String customerType, Pageable pageable) {
        Page<Customer> customerPage;
        if (customerType != null && !customerType.isBlank()) {
            if (!CustomerTypes.INDIVIDUAL.getCustomerType().equalsIgnoreCase(customerType) && !CustomerTypes.CORPORATE.getCustomerType().equalsIgnoreCase(customerType)) {
                throw new ResourceNotFoundException("Customer", "customerType", customerType);
            }
            customerPage = customerRepository.findAllByCustomerType(customerType, pageable);
        } else {
            customerPage = customerRepository.findAll(pageable);
        }
        log.info("Fetched all customer account details successfully");
        return customerPage.map(CustomerMapper::mapToCustomerDto);
    }

    @Override
    public boolean updateCustomerAccount(CustomerDto customerDto) {
        var optionalCustomer = customerRepository.findByCustomerAccountNumber(customerDto.getCustomerAccountNumber());

        if (optionalCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "customerAccountNumber", customerDto.getCustomerAccountNumber().toString());

        } else {

            CustomerMapper.updateCustomerAndMapTopCustomer(customerDto, optionalCustomer.get());
            customerRepository.save(optionalCustomer.get());
            log.info("Customer account details updated successfully");
            return true;
        }
    }

    @Override
    public boolean deleteAccount(Long customerAccountNumber) {
        customerRepository.findByCustomerAccountNumber(customerAccountNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "customerAccountNumber", customerAccountNumber.toString())
        );

        var deleted = customerRepository.deleteByCustomerAccountNumber(customerAccountNumber);
        log.info("Customer account details deleted successfully");
        return deleted > 0;
    }
}

