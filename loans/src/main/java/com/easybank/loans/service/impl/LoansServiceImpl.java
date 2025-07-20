package com.easybank.loans.service.impl;

import com.easybank.loans.dto.LoansDto;
import com.easybank.loans.entity.Loans;
import com.easybank.loans.exception.LoanAlreadyExistsException;
import com.easybank.loans.exception.ResourceNotFoundException;
import com.easybank.loans.mapper.LoansMapper;
import com.easybank.loans.repository.LoansRepository;
import com.easybank.loans.service.LoansServiceI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoansServiceImpl implements LoansServiceI {

    private final LoansRepository loansRepository;

    @Override
    public void createLoan(LoansDto loansDto) {
        var optionalLoans = loansRepository.findByLoanNumber(loansDto.getLoanNumber());
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given loanNumber " + loansDto.getLoanNumber());
        }
        loansRepository.save(optionalLoans.get());
    }


    @Override
    public LoansDto fetchLoan(String loanNumber) {
        var optionalLoans = loansRepository.findByLoanNumber(loanNumber);
        if (!optionalLoans.isPresent()) {
            throw new ResourceNotFoundException("Loan", "LoanNumber", loanNumber);
        }
        return LoansMapper.mapToLoansDto(optionalLoans.get());
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.updateLoansAndMapTopLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String loanNumber) {
        if (!loansRepository.findByLoanNumber(loanNumber).isPresent()) {
            throw new ResourceNotFoundException("Loan", "LoanNumber", loanNumber);
        }
        return loansRepository.deleteByLoanNumber(loanNumber);
    }


}
