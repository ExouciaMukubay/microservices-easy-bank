package com.easybank.accounts.repository;

import com.easybank.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerAccountNumber(Long customerAccountNumber);

    // List<Customer> findAll();

    Page<Customer> findAllByCustomerType(String customerType, Pageable pageable);

    @Transactional
    @Modifying
    int deleteByCustomerAccountNumber(Long customerAccountNumber);
}
