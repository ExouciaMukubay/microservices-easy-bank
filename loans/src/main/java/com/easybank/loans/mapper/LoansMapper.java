package com.easybank.loans.mapper;


import com.easybank.loans.dto.LoansDto;
import com.easybank.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoansDto(Loans loans) {
        return LoansDto.builder()
                .loanNumber(loans.getLoanNumber())
                .loanType(loans.getLoanType())
                .accountNumber(loans.getAccountNumber())
                .customerAccountNumber(loans.getCustomerAccountNumber())
                .totalLoan(loans.getTotalLoan())
                .amountPaid(loans.getAmountPaid())
                .outstandingAmount(loans.getOutstandingAmount())
                .build();

    }

    public static Loans mapToLoans(LoansDto loansDto) {
        return Loans.builder()
                .loanNumber(loansDto.getLoanNumber())
                .loanType(loansDto.getLoanType())
                .accountNumber(loansDto.getAccountNumber())
                .customerAccountNumber(loansDto.getCustomerAccountNumber())
                .totalLoan(loansDto.getTotalLoan())
                .amountPaid(loansDto.getAmountPaid())
                .outstandingAmount(loansDto.getOutstandingAmount())
                .build();
    }

    public static void updateLoansAndMapTopLoans(LoansDto loansDto, Loans loans) {

        if (loans.getTotalLoan() != loansDto.getTotalLoan()) {
            loans.setTotalLoan(loansDto.getTotalLoan());
        }

        if (loans.getAmountPaid() != loansDto.getAmountPaid()) {
            loans.setAmountPaid(loansDto.getAmountPaid());
        }

        if (loans.getOutstandingAmount() != loansDto.getOutstandingAmount()) {
            loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        }

    }
}
