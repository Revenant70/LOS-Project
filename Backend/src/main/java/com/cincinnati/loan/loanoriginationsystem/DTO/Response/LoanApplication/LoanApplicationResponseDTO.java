package com.cincinnati.loan.loanoriginationsystem.DTO.Response.LoanApplication;

import com.cincinnati.loan.loanoriginationsystem.DTO.Response.Borrower.BorrowerResponseDTO;

import java.util.UUID;

public record LoanApplicationResponseDTO(
        UUID applicationId,
        BorrowerResponseDTO borrower
) {
}
