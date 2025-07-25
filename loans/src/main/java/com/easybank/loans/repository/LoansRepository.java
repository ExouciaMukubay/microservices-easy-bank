package com.easybank.loans.repository;

import com.easybank.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {


    Optional<Loans> findByLoanNumber(String loanNumber);

    @Transactional
    @Modifying
    boolean deleteByLoanNumber(String loanNumber);

}
