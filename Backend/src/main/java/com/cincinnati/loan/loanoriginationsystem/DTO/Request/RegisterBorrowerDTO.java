package com.cincinnati.loan.loanoriginationsystem.DTO.Request;

import com.cincinnati.loan.loanoriginationsystem.Enums.Role;

public record RegisterBorrowerDTO(
        String borrowerName,
        String borrowerEmail,
        String borrowerPhoneNumber,
        String borrowerDOB,
        String borrowerSSN,
        String borrowerPassword,
        Role role
)
{}
