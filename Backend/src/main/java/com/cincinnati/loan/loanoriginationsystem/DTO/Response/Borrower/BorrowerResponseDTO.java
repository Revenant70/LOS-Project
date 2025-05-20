package com.cincinnati.loan.loanoriginationsystem.DTO.Response.Borrower;

import com.cincinnati.loan.loanoriginationsystem.Enums.Role;

import java.util.UUID;

public record BorrowerResponseDTO(
        String borrowerEmail,
        String borrowerPhoneNumber,
        Role role
) {
}
