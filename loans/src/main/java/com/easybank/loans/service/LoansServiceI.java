package com.easybank.loans.service;


import com.easybank.loans.dto.LoansDto;

public interface LoansServiceI {

    void createLoan(LoansDto loansDto);


    LoansDto fetchLoan(String loanNumber);

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String loanNumber);

}
