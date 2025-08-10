package com.easybank.accounts.service;

import com.easybank.accounts.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param customerAccountNumber
     * @return Accounts Details based on a given customerAccountNumber
     */
    CustomerDto fetchCustomerDetails(Long customerAccountNumber);

    Page<CustomerDto> getAllCustomers(String customerType, Pageable pageable);

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateCustomerAccount(CustomerDto customerDto);

    /**
     * @param customerAccountNumber
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(Long customerAccountNumber);

}
